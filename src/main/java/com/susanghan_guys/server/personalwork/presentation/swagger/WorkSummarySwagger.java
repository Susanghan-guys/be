package com.susanghan_guys.server.personalwork.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[출품작 요약]", description = "작품 요약 관련 API")
public interface WorkSummarySwagger {
    @Operation(
            summary = "DCA 개인 출품작 요약 생성 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DCA 개인 출품작에 대한 요약이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<WorkSummaryResponse> createDcaWorkSummary(
           @PathVariable(name = "workId") Long workId
    );

    @Operation(
            summary = "YCC 개인 출품작 요약 생성 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "YCC 개인 출품작에 대한 요약이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<WorkSummaryResponse> createYccWorkSummary(
            @PathVariable(name = "workId") Long workId
    );

    @Operation(
            summary = "개인 출품작 요약 조회 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "개인 출품작에 대한 요약이 성공적으로 조회되었습니다."
            )
    })
    CommonResponse<WorkSummaryResponse> getWorkSummary(@PathVariable Long workId);

}
