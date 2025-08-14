package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

@Builder
public record WorkEvaluationResponse(
        Integer score,
        String feasibility,
        String media,
        String agenda,
        String influence,
        String delivery
) {
}
