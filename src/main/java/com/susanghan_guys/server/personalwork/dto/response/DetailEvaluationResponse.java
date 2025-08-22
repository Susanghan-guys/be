package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DetailEvaluationResponse(
        List<DetailEvaluation> detailEvaluations
) {
    @Builder
    public record DetailEvaluation(
            String code,
            String label,
            Integer score,
            String description
    ) {}
}
