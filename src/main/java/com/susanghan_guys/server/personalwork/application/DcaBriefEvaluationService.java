package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.BriefAnalysis;
import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.dto.response.DcaBriefEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.BriefAnalysisMapper;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.EvaluationMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.BriefAnalysisRepository;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DcaBriefEvaluationService {


    private final CurrentUserProvider currentUserProvider;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;
    private final PersonalWorkValidator personalWorkValidator;
    private final WorkRepository workRepository;
    private final BriefAnalysisRepository briefAnalysisRepository;

    @Transactional
    public DcaBriefEvaluationResponse createDcaBriefEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();
        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        return getOrCreateBriefAnalysis(workId);
    }

    @Transactional(readOnly = true)
    public DcaBriefEvaluationResponse getDcaBriefEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();
        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        BriefAnalysis briefAnalysis = briefAnalysisRepository.findByWorkId(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.BRIEF_ANALYSIS_NOT_FOUND));

        DcaBriefEvaluationResponse response = BriefAnalysisMapper.toResponse(briefAnalysis);
        return response;
    }

    private DcaBriefEvaluationResponse getOrCreateBriefAnalysis(Long workId) {
        return briefAnalysisRepository.findByWorkId(workId)
                .map(BriefAnalysisMapper::toResponse)
                .orElseGet(() -> {
                    DcaOpenAiRequest request = openAiFactory.buildDcaOpenAiRequestWithBrief(workId);
                    DcaBriefEvaluationResponse response = openAiPort.createDcaBriefEvaluation(request);

                    Work work = workRepository.findById(workId)
                            .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

                    BriefAnalysis entity = BriefAnalysisMapper.toEntity(work, response);
                    briefAnalysisRepository.save(entity);

                    return response;
                });
    }

}