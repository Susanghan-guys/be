package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.Summary;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.SummaryMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.SummaryRepository;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkSummaryService {

    private final CurrentUserProvider currentUserProvider;
    private final WorkRepository workRepository;
    private final SummaryRepository summaryRepository;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;
    private final PersonalWorkValidator personalWorkValidator;

    @Transactional
    public WorkSummaryResponse createDcaWorkSummary(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        Summary summary = getOrCreateDcaWorkSummary(workId);

        return SummaryMapper.toResponse(summary);
    }

    @Transactional
    public WorkSummaryResponse createYccWorkSummary(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        Summary summary = getOrCreateYccWorkSummary(workId);

        return SummaryMapper.toResponse(summary);
    }

    private Summary getOrCreateDcaWorkSummary(Long workId) {
        return summaryRepository.findByWorkId(workId)
                .orElseGet(() -> {
                    Work work = workRepository.findById(workId)
                            .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

                    WorkSummaryResponse response = openAiPort.createWorkSummary(
                            openAiFactory.buildDcaOpenAiRequest(workId)
                    );

                    try {
                        return summaryRepository.saveAndFlush(SummaryMapper.toEntity(work, response));
                    } catch (DataIntegrityViolationException e) {
                        return summaryRepository.findByWorkId(workId)
                                .orElseThrow(() -> e);
                    }
                });
    }

    private Summary getOrCreateYccWorkSummary(Long workId) {
        return summaryRepository.findByWorkId(workId)
                .orElseGet(() -> {
                    Work work = workRepository.findById(workId)
                            .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

                    WorkSummaryResponse response = openAiPort.createWorkSummary(
                            openAiFactory.buildYccOpenAiRequest(workId)
                    );

                    try {
                        return summaryRepository.saveAndFlush(SummaryMapper.toEntity(work, response));
                    } catch (DataIntegrityViolationException e) {
                        return summaryRepository.findByWorkId(workId)
                                .orElseThrow(() -> e);
                    }
                });
    }

    public WorkSummaryResponse getWorkSummary(Long workId) {
        personalWorkValidator.validatePersonalWorkOwner(
                workId, currentUserProvider.getCurrentUser()
        );

        Summary summary = summaryRepository.findByWorkId(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.SUMMARY_NOT_FOUND));

        return SummaryMapper.toResponse(summary);
    }
}
