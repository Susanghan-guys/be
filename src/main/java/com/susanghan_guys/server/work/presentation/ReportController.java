package com.susanghan_guys.server.work.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.application.ReportService;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.dto.request.ReportDeletionRequest;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import com.susanghan_guys.server.work.dto.response.ReportCodeResponse;
import com.susanghan_guys.server.work.dto.response.ReportSharingResponse;
import com.susanghan_guys.server.work.dto.response.ReportInfoResponse;
import com.susanghan_guys.server.work.presentation.swagger.ReportSwagger;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.work.presentation.response.WorkSuccessCode.*;

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

    @Override
    @GetMapping("/{workId}")
    public CommonResponse<ReportInfoResponse> getReportInfo(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(MY_REPORTS_RETRIEVED_SUCCESS, reportService.getReportInfo(workId));
    }

    @Override
    @PostMapping("/{workId}")
    public CommonResponse<ReportSharingResponse> shareReport(@PathVariable(name = "workId") Long workId) {
        return CommonResponse.success(REPORTS_SHARE_SUCCESS, reportService.shareReport(workId));
    }

    @Override
    @PostMapping("/verify-code")
    public CommonResponse<ReportCodeResponse> verifyReportCode(@RequestBody @Valid ReportCodeRequest request) {
        return CommonResponse.success(REPORTS_CODE_VERIFY_SUCCESS, reportService.verifyReportCode(request));
    }

    @Override
    @PatchMapping("/{workId}/visibility")
    public CommonResponse<String> deleteReport(
            @PathVariable(name = "workId") Long workId,
            @RequestBody @Valid ReportDeletionRequest request
    ) {
        reportService.deleteReport(workId, request);
        return CommonResponse.success(MY_REPORTS_DELETED_SUCCESS, "OK");
    }
}
