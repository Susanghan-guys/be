package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

@Builder
public record WorkEvaluationResponse(
        String target,
        String brand,
        String agenda,
        String influence,
        String delivery
) {
}
