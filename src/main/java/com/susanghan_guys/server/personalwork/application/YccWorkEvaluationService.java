package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.YccDetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.EvaluationMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.EvaluationRepository;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YccWorkEvaluationService {

    private final CurrentUserProvider currentUserProvider;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;
    private final WorkRepository workRepository;
    private final EvaluationRepository evaluationRepository;
    private final PersonalWorkValidator personalWorkValidator;

    @Transactional
    public YccWorkEvaluationResponse createYccWorkEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        List<Evaluation> evaluations = getOrCreateEvaluation(workId);

        return EvaluationMapper.toResponse(evaluations);
    }

    public YccDetailEvaluationResponse createYccDetailEvaluation(Long workId, EvaluationType type) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        OpenAiRequest request = openAiFactory.buildYccOpenAiRequest(workId);

        // TODO: DB 저장 코드 구현

        return openAiPort.createDetailEvaluation(request, type);
    }

    private List<Evaluation> getOrCreateEvaluation(Long workId) {
        List<Evaluation> existing = evaluationRepository.findAllByWorkId(workId);
        if (!existing.isEmpty()) {
            return existing;
        }

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        YccWorkEvaluationResponse response = openAiPort.createWorkEvaluation(
                openAiFactory.buildYccOpenAiRequest(workId)
        );

        List<Evaluation> evaluations = EvaluationMapper.toEntities(work, response);
        return evaluationRepository.saveAll(evaluations);
    }
}
