package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.DetailEvalMapper;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.EvaluationMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.DetailEvalRepository;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.EvaluationRepository;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkVisibilityRepository;
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
    private final WorkVisibilityRepository workVisibilityRepository;
    private final EvaluationRepository evaluationRepository;
    private final DetailEvalRepository detailEvalRepository;
    private final PersonalWorkValidator personalWorkValidator;

    @Transactional
    public YccWorkEvaluationResponse createYccWorkEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        List<Evaluation> evaluations;
        if (personalWorkValidator.isOwner(workId, user.getId())) {
            evaluations = getOrCreateEvaluation(workId);
        } else if (workVisibilityRepository.existsByWorkIdAndUserIdAndVisibleTrue(workId, user.getId())) {
            evaluations = evaluationRepository.findAllByWorkId(workId);
        } else {
            throw new WorkException(WorkErrorCode.REPORT_UNAUTHORIZED);
        }

        return EvaluationMapper.toYccResponse(evaluations);
    }

    @Transactional
    public DetailEvaluationResponse createYccDetailEvaluation(Long workId, EvaluationType type) {
        User user = currentUserProvider.getCurrentUser();

        List<DetailEval> detailEvals;
        if (personalWorkValidator.isOwner(workId, user.getId())) {
            detailEvals = getOrCreateDetailEvaluation(workId, type);
        } else if (workVisibilityRepository.existsByWorkIdAndUserIdAndVisibleTrue(workId, user.getId())) {
            detailEvals = detailEvalRepository.findByWorkIdAndEvaluationType(workId, type);
        } else {
            throw new WorkException(WorkErrorCode.REPORT_UNAUTHORIZED);
        }

        return DetailEvalMapper.toResponse(detailEvals);
    }

    @Transactional(readOnly = true)
    public YccWorkEvaluationResponse getYccWorkEvaluation(Long workId) {

        personalWorkValidator.validatePersonalWorkOwner(workId, currentUserProvider.getCurrentUser());

        List<Evaluation> yccEvals = evaluationRepository
                .findAllByWorkIdAndTypeIn(workId, EvaluationType.yccTypes());

        if (yccEvals.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.EVALUATION_NOT_FOUND);
        }

        return EvaluationMapper.toYccResponse(yccEvals);
    }

    private List<Evaluation> getOrCreateEvaluation(Long workId) {
        List<Evaluation> existing = evaluationRepository.findAllByWorkId(workId);
        if (!existing.isEmpty()) {
            return existing;
        }

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        YccWorkEvaluationResponse response = openAiPort.createYccWorkEvaluation(
                openAiFactory.buildYccOpenAiRequest(workId)
        );

        List<Evaluation> evaluations = EvaluationMapper.toYccEntities(work, response);
        evaluationRepository.saveAll(evaluations);

        for (Evaluation evaluation : evaluations) {
            List<DetailEval> detailEvals = getOrCreateDetailEvaluation(workId, evaluation.getType());
            evaluation.updateScore(detailEvals);
        }

        return evaluations;
    }

    private List<DetailEval> getOrCreateDetailEvaluation(Long workId, EvaluationType type) {
        List<DetailEval> existing = detailEvalRepository.findByWorkIdAndEvaluationType(workId, type);
        if (!existing.isEmpty()) {
            return existing;
        }

        Evaluation evaluation = evaluationRepository.findByWorkIdAndType(workId, type)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.EVALUATION_NOT_FOUND));

        DetailEvaluationResponse response = openAiPort.createYccDetailEvaluation(
                openAiFactory.buildYccOpenAiRequest(workId), type
        );

        List<DetailEval> detailEvals = DetailEvalMapper.toEntities(evaluation, response);
        detailEvalRepository.saveAll(detailEvals);

        evaluation.updateScore(detailEvals);

        return detailEvals;
    }

    @Transactional
    public void runYccEvaluationInternal(Long workId) {
        getOrCreateEvaluation(workId);
    }
}
