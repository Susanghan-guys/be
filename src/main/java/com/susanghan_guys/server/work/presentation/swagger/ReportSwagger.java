package com.susanghan_guys.server.work.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "[리포트]", description = "리포트 관련 API")
public interface ReportSwagger {
    @Operation(
            summary = "내 리포트 조회 API",
            description = "제출된 내 리포트를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내 리포트 조회가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<MyReportListResponse> getMyReports(
            @RequestParam(name = "page") Integer page,
            @RequestParam(required = false) String name
    );
}
