package com.susanghan_guys.server.work.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.application.ReportService;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.dto.request.ReportDeletionRequest;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import com.susanghan_guys.server.work.presentation.swagger.ReportSwagger;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.work.presentation.response.WorkSuccessCode.MY_REPORTS_RETRIEVED_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class ReportController implements ReportSwagger {

    private final ReportService reportService;

    @Override
    @GetMapping
    public CommonResponse<MyReportListResponse> getMyReports(
            @RequestParam(name = "page") @Min(0) Integer page,
            @RequestParam(required = false) String name
    ) {
        return CommonResponse.success(MY_REPORTS_RETRIEVED_SUCCESS, reportService.getMyReports(name, page));
    }

    @PostMapping("/v1/{workId}/verify-code")
    public CommonResponse<String> verifyReportCode(
            @PathVariable(name = "workId") Long workId,
            @RequestBody @Valid ReportCodeRequest request
    ) {
        reportService.verifyReportCode(workId, request);
        return CommonResponse.success(MY_REPORTS_RETRIEVED_SUCCESS, "OK");
    }

    @PatchMapping("/v1/{workId}/visibility")
    public CommonResponse<String> deleteReport(
            @PathVariable(name = "workId") Long workId,
            @RequestBody @Valid ReportDeletionRequest request
    ) {
        reportService.deleteReport(workId, request);
        return CommonResponse.success(MY_REPORTS_RETRIEVED_SUCCESS, "OK");
    }
}
