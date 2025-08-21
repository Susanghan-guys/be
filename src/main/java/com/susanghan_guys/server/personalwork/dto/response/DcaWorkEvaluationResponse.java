package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

@Builder
public record DcaWorkEvaluationResponse(
        Integer targetScore,
        String  target,

        Integer brandScore,
        String  brand,

        Integer mediaScore,
        String  media,

        Integer problemScore,
        String  problem,

        Integer feasibilityScore,
        String  feasibility
) {}