package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record YccDetailEvaluationResponse(
        List<YccDetailEvaluation> detailEvaluations
) {

    @Builder
    public record YccDetailEvaluation(
            String code,
            String label,
            Integer score,
            String description
    ) {}
}
