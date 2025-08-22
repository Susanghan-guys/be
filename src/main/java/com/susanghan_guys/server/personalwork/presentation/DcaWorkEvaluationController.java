package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.DcaWorkEvaluationService;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works/dca")
public class DcaWorkEvaluationController {

    private final DcaWorkEvaluationService dcaWorkEvaluationService;

    @PostMapping("/{workId}/evaluation")
    public CommonResponse<DcaWorkEvaluationResponse> createDcaWorkEvaluation(@PathVariable Long workId) {
        return CommonResponse.success(DCA_WORK_EVALUATION_SUCCESS, dcaWorkEvaluationService.createDcaWorkEvaluation(workId));
    }

    @PostMapping("/{workId}/evaluation/{type}")
    public CommonResponse<DetailEvaluationResponse> createDcaDetailEvaluation(
            @PathVariable Long workId,
            @PathVariable EvaluationType type
    ) {
        return CommonResponse.success(DCA_WORK_DETAIL_EVALUATION_SUCCESS, dcaWorkEvaluationService.createDcaDetailEvaluation(workId, type));
    }
}
