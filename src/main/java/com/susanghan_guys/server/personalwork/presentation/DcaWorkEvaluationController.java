package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.DcaWorkEvaluationService;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.presentation.swagger.DcaWorkEvaluationSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class DcaWorkEvaluationController implements DcaWorkEvaluationSwagger {

    private final DcaWorkEvaluationService dcaWorkEvaluationService;

    @Override
    @PostMapping("/dca/{workId}/evaluation")
    public CommonResponse<DcaWorkEvaluationResponse> createDcaWorkEvaluation(@PathVariable Long workId) {
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
}
