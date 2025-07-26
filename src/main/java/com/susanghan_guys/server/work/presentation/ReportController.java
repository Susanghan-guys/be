package com.susanghan_guys.server.work.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.application.ReportService;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import com.susanghan_guys.server.work.presentation.swagger.ReportSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.global.common.code.SuccessCode.MY_REPORTS_RETRIEVED_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class ReportController implements ReportSwagger {

    private final ReportService totalWorkService;

    @Override
    @GetMapping
    public CommonResponse<MyReportListResponse> getMyReports(
            @RequestParam(name = "page") Integer page,
            @RequestParam(required = false) String name
    ) {
        return CommonResponse.success(MY_REPORTS_RETRIEVED_SUCCESS, totalWorkService.getMyReports(name, page));
    }
}
