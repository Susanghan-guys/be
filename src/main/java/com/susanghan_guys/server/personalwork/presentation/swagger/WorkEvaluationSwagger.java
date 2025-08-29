package com.susanghan_guys.server.personalwork.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[출품작 총평]", description = "작품 총평 관련 API")
public interface WorkEvaluationSwagger {
    @Operation(
            summary = "YCC 출품작 전체 총평 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "YCC 출품작에 대한 전체 총평이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<YccWorkEvaluationResponse> createYccWorkEvaluation(
            @PathVariable Long workId
    );

    @Operation(
            summary = "YCC 출품작 세부 총평 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID \n
                    `type`: YCC 전체 총평 카테고리
                    - `YCC_FEASIBILITY`: 실현 가능성
                    - `YCC_MEDIA_SELECTION`: 매체 선정
                    - `AGENDA_SELECTION`: 아젠다 선정
                    - `INFLUENCE`: 영향력
                    - `DELIVERY`: 전달력
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "YCC 출품작에 대한 세부 총평이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<DetailEvaluationResponse> createYccDetailEvaluation(
            @PathVariable Long workId,
            @PathVariable EvaluationType type
    );

    @Operation(
            summary = "DCA 출품작 전체 총평 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DCA 출품작에 대한 전체 총평이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<DcaWorkEvaluationResponse> createDcaWorkEvaluation(@PathVariable Long workId);

    @Operation(
            summary = "DCA 출품작 세부 총평 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID \n
                    `type`: DCA 전체 총평 카테고리
                    - `TARGET_FITNESS`: 타겟 적합성
                    - `BRAND_UNDERSTANDING`: 브랜드 이해도
                    - `DCA_MEDIA_SELECTION`: 매체 선정
                    - `PROBLEM_DEFINITION`: 문제 정의
                    - `DCA_FEASIBILITY`: 실현 가능성
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DCA 출품작에 대한 세부 총평이 성공적으로 생성되었습니다."
            )
    })
    CommonResponse<DetailEvaluationResponse> createDcaDetailEvaluation(
            @PathVariable Long workId,
            @PathVariable EvaluationType type
    );

    @Operation(
            summary = "DCA 출품작 전체 총평 조회 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DCA 출품작에 대한 전체 총평이 성공적으로 조회되었습니다."
            )
    })
    CommonResponse<DcaWorkEvaluationResponse> getDcaWorkEvaluation(@PathVariable Long workId);

    @Operation(
            summary = "YCC 출품작 전체 총평 조회 API",
            description = """
                    ### Pathvariable
                    ---
                    `workId` : 작품 고유 ID
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "YCC 출품작에 대한 전체 총평이 성공적으로 조회되었습니다."
            )
    })
    CommonResponse<YccWorkEvaluationResponse> getYccWorkEvaluation(@PathVariable Long workId);

    @Operation(
            summary = "출품작 세부 총평 조회 API",
            description = """
                    ### PathVariable
                    ---
                    `workId` : 작품 고유 ID \n
                    
                    `type`: 전체 총평 카테고리
                    
                    DCA
                    - `TARGET_FITNESS`: 타겟 적합성
                    - `BRAND_UNDERSTANDING`: 브랜드 이해도
                    - `DCA_MEDIA_SELECTION`: 매체 선정
                    - `PROBLEM_DEFINITION`: 문제 정의
                    - `DCA_FEASIBILITY`: 실현 가능성
                    
                    YCC
                    - `YCC_FEASIBILITY`: 실현 가능성
                    - `YCC_MEDIA_SELECTION`: 매체 선정
                    - `AGENDA_SELECTION`: 아젠다 선정
                    - `INFLUENCE`: 영향력
                    - `DELIVERY`: 전달력
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "출품작에 대한 세부 총평이 성공적으로 조회되었습니다."
            )
    })
    CommonResponse<DetailEvaluationResponse> getDetailEvaluation(@PathVariable Long workId, @PathVariable EvaluationType type);


}
