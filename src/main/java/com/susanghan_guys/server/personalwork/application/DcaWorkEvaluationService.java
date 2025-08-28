package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.DetailEvalMapper;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.EvaluationMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.DetailEvalRepository;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.EvaluationRepository;
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

import static com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode.EVALUATION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DcaWorkEvaluationService {

    private final CurrentUserProvider currentUserProvider;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;
    private final PersonalWorkValidator personalWorkValidator;
    private final EvaluationRepository evaluationRepository;
    private final DetailEvalRepository detailEvalRepository;
    private final WorkRepository workRepository;

    @Transactional
    public DcaWorkEvaluationResponse createDcaWorkEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);
        
        List<Evaluation> evaluations = getOrCreateEvaluation(workId);

        return EvaluationMapper.toDcaResponse(evaluations);
    }

    @Transactional
    public DetailEvaluationResponse createDcaDetailEvaluation(Long workId, EvaluationType type) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        List<DetailEval> detailEvals = getOrCreateDetailEvaluation(workId, type);

        return DetailEvalMapper.toResponse(detailEvals);
    }

    @Transactional(readOnly = true)
    public DcaWorkEvaluationResponse getDcaWorkEvaluation(Long workId) {

        personalWorkValidator.validatePersonalWorkOwner(workId, currentUserProvider.getCurrentUser());

        List<Evaluation> dcaEvals = evaluationRepository
                .findAllByWorkIdAndTypeIn(workId, EvaluationType.dcaTypes());

        if (dcaEvals.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.EVALUATION_NOT_FOUND);
        }

        return EvaluationMapper.toDcaResponse(dcaEvals);
    }

    private List<Evaluation> getOrCreateEvaluation(Long workId) {
        List<Evaluation> existing = evaluationRepository.findAllByWorkId(workId);
        if (!existing.isEmpty()) {
            return existing;
        }

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        DcaWorkEvaluationResponse response = openAiPort.createDcaWorkEvaluation(
                openAiFactory.buildDcaOpenAiRequestWithBrief(workId)
        );

        List<Evaluation> evaluations = EvaluationMapper.toDcaEntities(work, response);
        evaluationRepository.saveAll(evaluations);

        for (Evaluation evaluation : evaluations) {
            List<DetailEval> detailEvals = getOrCreateDetailEvaluation(workId, evaluation.getType());
            evaluation.updateScore(detailEvals);
        }
        work.updateReportStatus(ReportStatus.COMPLETED);

        return evaluations;
    }

    private List<DetailEval> getOrCreateDetailEvaluation(Long workId, EvaluationType type) {
        List<DetailEval> existing = detailEvalRepository.findByWorkIdAndEvaluationType(workId, type);
        if (!existing.isEmpty()) {
            return existing;
        }

        Evaluation evaluation = evaluationRepository.findByWorkIdAndType(workId, type)
                .orElseThrow(() -> new PersonalWorkException(EVALUATION_NOT_FOUND));

        DetailEvaluationResponse response = openAiPort.createDcaDetailEvaluation(
                openAiFactory.buildDcaOpenAiRequestWithBrief(workId), type
        );

        List<DetailEval> detailEvals = DetailEvalMapper.toEntities(evaluation, response);
        detailEvalRepository.saveAll(detailEvals);

        evaluation.updateScore(detailEvals);

        return detailEvals;
    }

}
