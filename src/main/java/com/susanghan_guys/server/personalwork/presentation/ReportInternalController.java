package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.ReportInternalService;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.ReportPipelineResponse;
import com.susanghan_guys.server.personalwork.presentation.swagger.ReportInternalSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/internal/reports")
public class ReportInternalController implements ReportInternalSwagger {

    private final ReportInternalService reportInternalService;

    @Override
    @PostMapping("/{workId}/pipeline")
    public CommonResponse<ReportPipelineResponse> runPipeline(@PathVariable Long workId) {
        ReportPipelineResponse result = reportInternalService.runPipeline(workId);
        return CommonResponse.success(REPORT_PIPELINE_SUCCESS, result);
    }

    @Override
    @DeleteMapping("/{workId}/evaluations")
    public CommonResponse<String> deleteAllEvaluations(@PathVariable Long workId) {
        reportInternalService.deleteAllEvaluationsByWorkId(workId);
        return CommonResponse.success(WORK_EVALUATIONS_DELETE_SUCCESS, "OK");
    }

    @Override
    @DeleteMapping("/{workId}/evaluations/{type}/details")
    public CommonResponse<String> deleteDetailEvaluations(
            @PathVariable Long workId,
            @PathVariable EvaluationType type
    ) {
        reportInternalService.deleteDetailEvalsByWorkIdAndType(workId, type);
        return CommonResponse.success(DETAIL_EVALUATION_DELETE_SUCCESS, "OK");
    }
}