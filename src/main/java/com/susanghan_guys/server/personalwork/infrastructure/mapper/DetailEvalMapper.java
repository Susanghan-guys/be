package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.DetailEvalType;
import com.susanghan_guys.server.personalwork.dto.response.YccDetailEvaluationResponse;

import java.util.List;

public class DetailEvalMapper {

    public static DetailEval toEntity(Evaluation evaluation, String content, Integer score, DetailEvalType type) {
        return DetailEval.builder()
                .evaluation(evaluation)
                .content(content)
                .score(score)
                .type(type)
                .build();
    }

    public static List<DetailEval> toEntities(Evaluation evaluation, YccDetailEvaluationResponse response) {
        return response.detailEvaluations().stream()
                .map(d -> toEntity(
                        evaluation,
                        d.description(),
                        d.score(),
                        DetailEvalType.valueOf(d.code())
                ))
                .toList();
    }

    public static YccDetailEvaluationResponse toResponse(List<DetailEval> detailEvals) {
        return YccDetailEvaluationResponse.builder()
                .detailEvaluations(detailEvals.stream()
                        .map(e -> YccDetailEvaluationResponse.YccDetailEvaluation.builder()
                                .code(e.getType().name())
                                .label(e.getType().getLabel())
                                .score(e.getScore())
                                .description(e.getContent())
                                .build()
                        )
                        .toList())
                .build();
    }
}
