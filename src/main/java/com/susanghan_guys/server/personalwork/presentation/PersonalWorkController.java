package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.PersonalWorkService;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.WORK_SUMMARY_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class PersonalWorkController {

    private final PersonalWorkService personalWorkService;

    @PostMapping("/dca/{workId}/summary")
    public CommonResponse<WorkSummaryResponse> createDcaWorkSummary(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(WORK_SUMMARY_SUCCESS, personalWorkService.createDcaWorkSummary(workId));
    }

    @PostMapping("/ycc/{workId}/summary")
    public CommonResponse<WorkSummaryResponse> createYccWorkSummary(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(WORK_SUMMARY_SUCCESS, personalWorkService.createYccWorkSummary(workId));
    }
}
