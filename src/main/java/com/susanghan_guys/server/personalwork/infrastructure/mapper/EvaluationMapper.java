package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.work.domain.Work;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EvaluationMapper {

    public static Evaluation toEntity(Work work, String content, double score, EvaluationType type) {
        return Evaluation.builder()
                .work(work)
                .content(content)
                .score(score)
                .type(type)
                .build();
    }

    public static List<Evaluation> toEntities(Work work, YccWorkEvaluationResponse response) {
        return List.of(
                toEntity(work, response.feasibility(), response.feasibilityScore(), EvaluationType.YCC_FEASIBILITY),
                toEntity(work, response.media(), response.mediaScore(), EvaluationType.YCC_MEDIA_SELECTION),
                toEntity(work, response.agenda(), response.agendaScore(), EvaluationType.AGENDA_SELECTION),
                toEntity(work, response.influence(), response.influenceScore(), EvaluationType.INFLUENCE),
                toEntity(work, response.delivery(), response.deliveryScore(), EvaluationType.DELIVERY)
        );
    }

    public static YccWorkEvaluationResponse toResponse(List<Evaluation> evaluations) {
        Map<EvaluationType, Evaluation> map = evaluations.stream()
                .collect(Collectors.toMap(Evaluation::getType, e -> e));

        return new YccWorkEvaluationResponse(
                Optional.of(map.get(EvaluationType.YCC_FEASIBILITY).getScore()).orElse(0.0),
                map.get(EvaluationType.YCC_FEASIBILITY).getContent(),
                Optional.of(map.get(EvaluationType.YCC_MEDIA_SELECTION).getScore()).orElse(0.0),
                map.get(EvaluationType.YCC_MEDIA_SELECTION).getContent(),
                Optional.of(map.get(EvaluationType.AGENDA_SELECTION).getScore()).orElse(0.0),
                map.get(EvaluationType.AGENDA_SELECTION).getContent(),
                Optional.of(map.get(EvaluationType.INFLUENCE).getScore()).orElse(0.0),
                map.get(EvaluationType.INFLUENCE).getContent(),
                Optional.of(map.get(EvaluationType.DELIVERY).getScore()).orElse(0.0),
                map.get(EvaluationType.DELIVERY).getContent()
        );
    }
}
