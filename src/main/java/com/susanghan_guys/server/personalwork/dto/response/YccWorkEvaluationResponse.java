package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

@Builder
public record YccWorkEvaluationResponse(
        double feasibilityScore,
        String feasibility,
        double mediaScore,
        String media,
        double agendaScore,
        String agenda,
        double influenceScore,
        String influence,
        double deliveryScore,
        String delivery
) {
}
