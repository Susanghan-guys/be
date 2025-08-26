package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.DetailEvalType;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;

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

    public static List<DetailEval> toEntities(Evaluation evaluation, DetailEvaluationResponse response) {
        return response.detailEvaluations().stream()
                .map(d -> toEntity(
                        evaluation,
                        d.description(),
                        d.score(),
                        DetailEvalType.from(d.code())
                ))
                .toList();
    }

    public static DetailEvaluationResponse toResponse(List<DetailEval> detailEvals) {
        return DetailEvaluationResponse.builder()
                .detailEvaluations(detailEvals.stream()
                        .map(e -> DetailEvaluationResponse.DetailEvaluation.builder()
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
