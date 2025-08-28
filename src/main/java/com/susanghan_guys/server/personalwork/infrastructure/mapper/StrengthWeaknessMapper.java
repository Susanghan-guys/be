package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse.DetailEvaluation;

public class StrengthWeaknessMapper {

    public static DetailEvaluation toResponse(DetailEval e) {
        return DetailEvaluation.builder()
                .code(e.getType().name())
                .label(e.getType().getLabel())
                .score(e.getScore())
                .description(e.getContent())
                .build();
    }
}