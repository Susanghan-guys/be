package com.susanghan_guys.server.personalwork.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "[출품작 강점/보완점]", description = "출품작 강점/보완점 조회 API")
public interface StrengthWeaknessSwagger {

    @Operation(
            summary = "개인 출품작 강점 조회 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    
                    ### 정렬 규칙
                    - 세부총평 점수 내림차순 Top3 (동점은 전체총평 점수 높은 순)
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "출품작에 대한 강점 조회가 성공적으로 처리되었습니다."
            )
    })
    CommonResponse<List<DetailEvaluationResponse.DetailEvaluation>> getStrengths(
            @PathVariable(name = "workId") Long workId
    );

    @Operation(
            summary = "개인 출품작 보완점 조회 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    
                    ### 정렬 규칙
                    - 세부총평 점수 오름차순 최대 3개 (두개가 반환되는 경우도 있습니다.)
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "출품작에 대한 보완점 조회가 성공적으로 처리되었습니다."
            )
    })
    CommonResponse<List<DetailEvaluationResponse.DetailEvaluation>> getWeaknesses(
            @PathVariable(name = "workId") Long workId
    );
}