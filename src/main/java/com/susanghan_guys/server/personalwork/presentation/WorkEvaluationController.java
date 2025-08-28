package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.DcaWorkEvaluationService;
import com.susanghan_guys.server.personalwork.application.WorkReadService;
import com.susanghan_guys.server.personalwork.application.YccWorkEvaluationService;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.presentation.swagger.WorkEvaluationSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class WorkEvaluationController implements WorkEvaluationSwagger {

    private final YccWorkEvaluationService yccWorkEvaluationService;
    private final DcaWorkEvaluationService dcaWorkEvaluationService;
    private final WorkReadService workReadService;

    @Override
    @PostMapping("/ycc/{workId}/evaluation")
    public CommonResponse<YccWorkEvaluationResponse> createYccWorkEvaluation(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(YCC_WORK_EVALUATION_SUCCESS, yccWorkEvaluationService.createYccWorkEvaluation(workId));
    }

    @Override
    @PostMapping("/ycc/{workId}/evaluation/{type}")
    public CommonResponse<DetailEvaluationResponse> createYccDetailEvaluation(
            @PathVariable(name = "workId") Long workId,
            @PathVariable EvaluationType type
    ) {
        return CommonResponse.success(YCC_WORK_DETAIL_EVALUATION_SUCCESS, yccWorkEvaluationService.createYccDetailEvaluation(workId, type));
    }

    @Override
    @PostMapping("/dca/{workId}/evaluation")
    public CommonResponse<DcaWorkEvaluationResponse> createDcaWorkEvaluation(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(DCA_WORK_EVALUATION_SUCCESS, dcaWorkEvaluationService.createDcaWorkEvaluation(workId));
    }

    @Override
    @PostMapping("/dca/{workId}/evaluation/{type}")
    public CommonResponse<DetailEvaluationResponse> createDcaDetailEvaluation(
            @PathVariable Long workId,
            @PathVariable EvaluationType type
    ) {
        return CommonResponse.success(DCA_WORK_DETAIL_EVALUATION_SUCCESS, dcaWorkEvaluationService.createDcaDetailEvaluation(workId, type));
    }

    @Override
    @GetMapping("/dca/{workId}/evaluation")
    public CommonResponse<DcaWorkEvaluationResponse> getDcaWorkEvaluation(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(DCA_WORK_EVALUATION_SUCCESS, dcaWorkEvaluationService.getDcaWorkEvaluation(workId));
    }

    @Override
    @GetMapping("/ycc/{workId}/evaluation")
    public CommonResponse<YccWorkEvaluationResponse> getYccWorkEvaluation(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(YCC_WORK_EVALUATION_SUCCESS, yccWorkEvaluationService.getYccWorkEvaluation(workId));
    }

    @Override
    @GetMapping("/{workId}/evaluation/{type}")
    public CommonResponse<DetailEvaluationResponse> getDetailEvaluation(
            @PathVariable(name = "workId") Long workId,
            @PathVariable EvaluationType type) {
        return CommonResponse.success(WORK_DETAILS_FETCH_SUCCESS, workReadService.getDetailEvaluation(workId, type));
    }
}
