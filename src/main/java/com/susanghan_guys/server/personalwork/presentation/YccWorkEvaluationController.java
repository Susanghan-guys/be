package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.YccWorkEvaluationService;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.YCC_WORK_DETAIL_EVALUATION_SUCCESS;
import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.YCC_WORK_EVALUATION_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works/ycc")
public class YccWorkEvaluationController {

    private final YccWorkEvaluationService yccWorkEvaluationService;

    @PostMapping("/{workId}/evaluation")
    public CommonResponse<YccWorkEvaluationResponse> createYccWorkEvaluation(@PathVariable Long workId) {
        return CommonResponse.success(YCC_WORK_EVALUATION_SUCCESS, yccWorkEvaluationService.createYccWorkEvaluation(workId));
    }

    @PostMapping("/{workId}/evaluation/{type}")
    public CommonResponse<DetailEvaluationResponse> createYccDetailEvaluation(
            @PathVariable Long workId,
            @PathVariable EvaluationType type
    ) {
        return CommonResponse.success(YCC_WORK_DETAIL_EVALUATION_SUCCESS, yccWorkEvaluationService.createYccDetailEvaluation(workId, type));
    }
}
