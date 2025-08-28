package com.susanghan_guys.server.personalwork.presentation.swagger;


import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.dto.response.DcaBriefEvaluationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[출품작 브리프 해석]", description = "DCA 브리프 해석 관련 API")
public interface DcaBriefEvaluationSwagger {

    @Operation(
            summary = "DCA 출품작 브리프 해석 API",
            description = """
            ### PathVariable
            - `workId` : 작품 고유 ID
            
            ### Response
            - interpretation : 브리프 해석
            - consistency    : 반영 일관성
            - weakness       : 보완점
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DCA 출품작에 대한 브리프 해석이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<DcaBriefEvaluationResponse> createDcaBriefEvaluation(@PathVariable Long workId);

    @Operation(
            summary = "DCA 출품작 브리프 해석 조회 API",
            description = """
            ### PathVariable
            - `workId` : 작품 고유 ID
            
            ### Response
            - interpretation : 브리프 해석
            - consistency    : 반영 일관성
            - weakness       : 보완점
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DCA 출품작에 대한 브리프 해석이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<DcaBriefEvaluationResponse> getDcaBriefEvaluation(@PathVariable Long workId);
}
