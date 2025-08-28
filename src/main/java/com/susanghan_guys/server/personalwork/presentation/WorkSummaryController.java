package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.WorkSummaryService;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.personalwork.presentation.swagger.WorkSummarySwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class WorkSummaryController implements WorkSummarySwagger {

    private final WorkSummaryService workSummaryService;

    @Override
    @PostMapping("/dca/{workId}/summary")
    public CommonResponse<WorkSummaryResponse> createDcaWorkSummary(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(DCA_WORK_SUMMARY_SUCCESS, workSummaryService.createDcaWorkSummary(workId));
    }

    @Override
    @PostMapping("/ycc/{workId}/summary")
    public CommonResponse<WorkSummaryResponse> createYccWorkSummary(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(YCC_WORK_SUMMARY_SUCCESS, workSummaryService.createYccWorkSummary(workId));
    }

    @Override
    @GetMapping("/{workId}/summary")
    public CommonResponse<WorkSummaryResponse> getWorkSummary(@PathVariable Long workId) {
        return CommonResponse.success(WORK_SUMMARY_SUCCESS, workSummaryService.getWorkSummary(workId));
    }
}
