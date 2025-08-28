package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.DcaBriefEvaluationService;
import com.susanghan_guys.server.personalwork.dto.response.DcaBriefEvaluationResponse;
import com.susanghan_guys.server.personalwork.presentation.swagger.DcaBriefEvaluationSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.DCA_BRIEF_EVALUATION_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works/dca")
public class DcaBriefEvaluationController implements DcaBriefEvaluationSwagger {

    private final DcaBriefEvaluationService dcaBriefEvaluationService;

    @Override
    @PostMapping("/{workId}/brief-evaluation")
    public CommonResponse<DcaBriefEvaluationResponse> createDcaBriefEvaluation(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(DCA_BRIEF_EVALUATION_SUCCESS, dcaBriefEvaluationService.createDcaBriefEvaluation(workId));
    }

    @Override
    @GetMapping("/{workId}/brief-evaluation")
    public CommonResponse<DcaBriefEvaluationResponse> getDcaBriefEvaluation(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(DCA_BRIEF_EVALUATION_SUCCESS, dcaBriefEvaluationService.getDcaBriefEvaluation(workId));
    }
}
