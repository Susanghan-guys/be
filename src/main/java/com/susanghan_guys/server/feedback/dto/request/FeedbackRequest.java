package com.susanghan_guys.server.feedback.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "내 리포트 피드백 API")
public record FeedbackRequest(
        @Schema(description = "만족도 점수", example = "5")
        Integer score,

        @Schema(description = "리포트 후기", example = "우와 너무 좋아요")
        String content
) {
}
