package com.susanghan_guys.server.personalwork.presentation.swagger;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.ReportPipelineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[백엔드 내부 - 리포트 생성 및 삭제]", description = "요약/브리프/총평을 일괄 생성 API")
public interface ReportInternalSwagger {

    @Operation(
            summary = "리포트 파이프라인 실행 API(workType에 따라 분기)",
            description = """
                    ### Pathvariable
                    `workId` : 작품 고유 ID
                    
                    ### 동작
                    - DCA: 요약 → 브리프 해석 → 총평
                    - YCC: 요약 → 총평
                    """
    )
    @ApiResponse(responseCode = "200", description = "수상 리포트 생성 파이프라인이 실행되었습니다.")
    CommonResponse<ReportPipelineResponse> runPipeline(@PathVariable Long workId);

    @Operation(
            summary = "작품 전체 총평/세부 총평 삭제 API(내부용)",
            description = """
                    ### Pathvariable
                    - `workId`: 작품 고유 ID
                    
                    ### 동작
                    - 해당 작품의 모든 Evaluation(전체 총평) 삭제
                    - 각 Evaluation에 매핑된 DetailEval도 함께 삭제
                    """
    )
    CommonResponse<String> deleteAllEvaluations(@PathVariable Long workId);

    @Operation(
            summary = "작품 특정 타입 총평/세부 총평 삭제 API(내부용)",
            description = """
                    ### Pathvariable
                    - `workId`: 작품 고유 ID
                    - `type`: EvaluationType (예: TARGET_FITNESS, BRAND_UNDERSTANDING, ...)

                    ### 동작
                    - 지정한 작품(workId)의 특정 타입 Evaluation은 그대로 두고,
                      그 Evaluation에 연결된 DetailEval(세부 총평)만 삭제합니다.
                    """
    )
    CommonResponse<String> deleteDetailEvaluations(@PathVariable Long workId, @PathVariable EvaluationType type);
}