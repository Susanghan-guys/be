package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

@Builder
public record WorkEvaluationResponse(
        Integer feasibilityScore,
        String feasibility,
        Integer mediaScore,
        String media,
        Integer agendaScore,
        String agenda,
        Integer influenceScore,
        String influence,
        Integer deliveryScore,
        String delivery
) {
}
