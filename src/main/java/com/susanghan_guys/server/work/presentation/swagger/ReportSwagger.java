package com.susanghan_guys.server.work.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.dto.request.ReportDeletionRequest;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import com.susanghan_guys.server.work.dto.response.ReportCodeResponse;
import com.susanghan_guys.server.work.dto.response.ReportSharingResponse;
import com.susanghan_guys.server.work.dto.response.ReportInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
            @RequestParam(name = "page") @Min(0) Integer page,
            @RequestParam(required = false) String name
    );

    @Operation(
            summary = "리포트 정보 조회 API",
            description = "분석 완료된 출품작에 대한 리포트 정보를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "리포트 정보에 대한 조회가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<ReportInfoResponse> getReportInfo(
            @PathVariable(name = "workId") Long workId
    );

    @Operation(
            summary = "리포트 공유 API",
            description = "리포트 링크와 코드를 타인에게 공유합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "리포트 공유가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<ReportSharingResponse> shareReport(
            @PathVariable(name = "workId") Long workId
    );

    @Operation(
            summary = "리포트 코드 인증 API",
            description = "팀원이 리포트를 조회할 경우, 리포트 코드 인증을 진행합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "리포트 코드 인증이 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<ReportCodeResponse> verifyReportCode(
            @PathVariable(name = "workId") Long workId,
            @RequestBody @Valid ReportCodeRequest request
    );

    @Operation(
            summary = "리포트 삭제 API",
            description = "팀원일 경우에만 리포트 삭제를 진행합니다. (신청자일 경우, 삭제 불가능)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "리포트 삭제가 성공적으로 실행되었습니다."
            )
    })
    CommonResponse<String> deleteReport(
            @PathVariable(name = "workId") Long workId,
            @RequestBody @Valid ReportDeletionRequest request
    );
}
