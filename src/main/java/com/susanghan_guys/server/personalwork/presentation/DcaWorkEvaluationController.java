package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.DcaWorkEvaluationService;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.DCA_WORK_SUMMARY_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class DcaWorkEvaluationController {

    private final DcaWorkEvaluationService dcaWorkEvaluationService;

    @PostMapping("/dca/{workId}/evaluation")
    public CommonResponse<DcaWorkEvaluationResponse> createDcaWorkEvaluation(@PathVariable Long workId) {
        return CommonResponse.success(DCA_WORK_SUMMARY_SUCCESS, dcaWorkEvaluationService.createDcaWorkEvaluation(workId));
    }
}
