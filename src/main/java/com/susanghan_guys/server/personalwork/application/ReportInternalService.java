package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.ReportPipelineResponse;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.DetailEvalRepository;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.EvaluationRepository;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.WorkType;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportInternalService {

    private final WorkRepository workRepository;
    private final WorkSummaryService workSummaryService;
    private final DcaBriefEvaluationService dcaBriefEvaluationService;
    private final DcaWorkEvaluationService dcaWorkEvaluationService;
    private final YccWorkEvaluationService yccWorkEvaluationService;
    private final EvaluationRepository evaluationRepository;
    private final DetailEvalRepository detailEvalRepository;


    @Transactional
    public ReportPipelineResponse runPipeline(Long workId) {
        boolean summaryDone = false;
        boolean briefDone = false;
        boolean evaluationDone = false;

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        workSummaryService.runSummaryInternal(workId);
        summaryDone = true;

        if (work.getType() == WorkType.DCA) {
            dcaBriefEvaluationService.runBriefInternal(workId);
            briefDone = true;
        }

        runEvaluationInternal(workId);
        evaluationDone = true;

        return ReportPipelineResponse.builder()
                .summaryDone(summaryDone)
                .briefDone(briefDone)
                .evaluationDone(evaluationDone)
                .build();
    }

    @Transactional
    public void deleteAllEvaluationsByWorkId(Long workId) {
        // 작품 유효성
        workRepository.findById(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.WORK_NOT_FOUND));

        // 부모 엔티티를 엔티티 삭제(remove)로 지워야 orphanRemoval이 동작함
        List<Evaluation> evaluations = evaluationRepository.findAllByWorkId(workId);
        if (!evaluations.isEmpty()) {
            evaluationRepository.deleteAll(evaluations); // 각 엔티티 remove -> 자식(DetailEval)도 함께 제거
        }
    }

    @Transactional
    public void deleteDetailEvalsByWorkIdAndType(Long workId, EvaluationType type) {
        workRepository.findById(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.WORK_NOT_FOUND));

        Evaluation evaluation = evaluationRepository.findByWorkIdAndType(workId, type)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.EVALUATION_NOT_FOUND));

        if (!evaluation.getDetails().isEmpty()) {
            detailEvalRepository.deleteAll(evaluation.getDetails());
            evaluation.getDetails().clear();
        }
    }

    public void runEvaluationInternal(Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        switch (work.getType()) {
            case DCA -> dcaWorkEvaluationService.runDcaEvaluationInternal(workId);
            case YCC -> yccWorkEvaluationService.runYccEvaluationInternal(workId);
            default -> throw new PersonalWorkException(PersonalWorkErrorCode.UNSUPPORTED_WORK_TYPE);
        }
    }
}