package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.YccDetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.DetailEvalMapper;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.EvaluationMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.DetailEvalRepository;
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
    private final DetailEvalRepository detailEvalRepository;
    private final PersonalWorkValidator personalWorkValidator;

    @Transactional
    public YccWorkEvaluationResponse createYccWorkEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        List<Evaluation> evaluations = getOrCreateEvaluation(workId);

        return EvaluationMapper.toResponse(evaluations);
    }

    @Transactional
    public YccDetailEvaluationResponse createYccDetailEvaluation(Long workId, EvaluationType type) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        List<DetailEval> detailEvals = getOrCreateDetailEvaluation(workId, type);

        return DetailEvalMapper.toResponse(detailEvals);
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

    private List<DetailEval> getOrCreateDetailEvaluation(Long workId, EvaluationType type) {
        List<DetailEval> existing = detailEvalRepository.findByWorkIdAndEvaluationType(workId, type);
        if (!existing.isEmpty()) {
            return existing;
        }

        Evaluation evaluation = evaluationRepository.findByWorkIdAndType(workId, type)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND)); // TODO: 에러 코드 수정

        YccDetailEvaluationResponse response = openAiPort.createDetailEvaluation(
                openAiFactory.buildYccOpenAiRequest(workId), type
        );

        List<DetailEval> detailEvals = DetailEvalMapper.toEntities(evaluation, response);
        detailEvalRepository.saveAll(detailEvals);

        evaluation.updateScore(detailEvals);

        return detailEvals;
    }
}
