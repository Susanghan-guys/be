package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.WorkEvaluationService;
import com.susanghan_guys.server.personalwork.dto.response.WorkEvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.YCC_WORK_SUMMARY_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class WorkEvaluationController {

    private final WorkEvaluationService workEvaluationService;

    @PostMapping("/ycc/{workId}/evaluation")
    public CommonResponse<WorkEvaluationResponse> createYccWorkEvaluation(@PathVariable Long workId) {
        return CommonResponse.success(YCC_WORK_SUMMARY_SUCCESS, workEvaluationService.createYccWorkEvaluation(workId));
    }
}
