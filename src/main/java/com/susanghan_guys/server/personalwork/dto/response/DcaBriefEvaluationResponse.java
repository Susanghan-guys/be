package com.susanghan_guys.server.personalwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record DcaBriefEvaluationResponse(

        @Schema(
                description = "Interpretation",
                example = "브리프 해석이 반환되는 필드입니다."
        )
        String interpretation,

        @Schema(
                description = "Consistency",
                example = "반영 일관성이 반환되는 필드입니다."
        )
        String consistency,

        @Schema(
                description = "Weakness",
                example = "보완점이 반환되는 필드입니다."
        )
        String weakness
) {
}
