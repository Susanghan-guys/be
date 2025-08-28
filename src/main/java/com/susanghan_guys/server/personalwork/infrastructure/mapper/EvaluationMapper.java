package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.Evaluation;
import com.susanghan_guys.server.personalwork.domain.support.ScoreCalculator;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
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

    public static List<Evaluation> toYccEntities(Work work, YccWorkEvaluationResponse response) {
        return List.of(
                toEntity(work, response.feasibility(), response.feasibilityScore(), EvaluationType.YCC_FEASIBILITY),
                toEntity(work, response.media(), response.mediaScore(), EvaluationType.YCC_MEDIA_SELECTION),
                toEntity(work, response.agenda(), response.agendaScore(), EvaluationType.AGENDA_SELECTION),
                toEntity(work, response.influence(), response.influenceScore(), EvaluationType.INFLUENCE),
                toEntity(work, response.delivery(), response.deliveryScore(), EvaluationType.DELIVERY)
        );
    }

    public static List<Evaluation> toDcaEntities(Work work, DcaWorkEvaluationResponse response) {
        return List.of(
                toEntity(work, response.target(), response.targetScore(), EvaluationType.TARGET_FITNESS),
                toEntity(work, response.brand(), response.brandScore(), EvaluationType.BRAND_UNDERSTANDING),
                toEntity(work, response.media(), response.mediaScore(), EvaluationType.DCA_MEDIA_SELECTION),
                toEntity(work, response.problem(), response.problemScore(), EvaluationType.PROBLEM_DEFINITION),
                toEntity(work, response.feasibility(), response.feasibilityScore(), EvaluationType.DCA_FEASIBILITY)
        );
    }

    public static YccWorkEvaluationResponse toYccResponse(List<Evaluation> evaluations) {
        Map<EvaluationType, Evaluation> map = evaluations.stream()
                .collect(Collectors.toMap(Evaluation::getType, e -> e));

        return new YccWorkEvaluationResponse(
                ScoreCalculator.calculateTotalScore(evaluations),
                getScore(map, EvaluationType.YCC_FEASIBILITY),
                getContent(map, EvaluationType.YCC_FEASIBILITY),
                getScore(map, EvaluationType.YCC_MEDIA_SELECTION),
                getContent(map, EvaluationType.YCC_MEDIA_SELECTION),
                getScore(map, EvaluationType.AGENDA_SELECTION),
                getContent(map, EvaluationType.AGENDA_SELECTION),
                getScore(map, EvaluationType.INFLUENCE),
                getContent(map, EvaluationType.INFLUENCE),
                getScore(map, EvaluationType.DELIVERY),
                getContent(map, EvaluationType.DELIVERY)
        );
    }

    public static DcaWorkEvaluationResponse toDcaResponse(List<Evaluation> evaluations) {
        Map<EvaluationType, Evaluation> map = evaluations.stream()
                .collect(Collectors.toMap(Evaluation::getType, e -> e));

        return new DcaWorkEvaluationResponse(
                ScoreCalculator.calculateTotalScore(evaluations),
                getScore(map, EvaluationType.TARGET_FITNESS),
                getContent(map, EvaluationType.TARGET_FITNESS),
                getScore(map, EvaluationType.BRAND_UNDERSTANDING),
                getContent(map, EvaluationType.BRAND_UNDERSTANDING),
                getScore(map, EvaluationType.DCA_MEDIA_SELECTION),
                getContent(map, EvaluationType.DCA_MEDIA_SELECTION),
                getScore(map, EvaluationType.PROBLEM_DEFINITION),
                getContent(map, EvaluationType.PROBLEM_DEFINITION),
                getScore(map, EvaluationType.DCA_FEASIBILITY),
                getContent(map, EvaluationType.DCA_FEASIBILITY)
        );
    }

    private static double getScore(Map<EvaluationType, Evaluation> map, EvaluationType type) {
        return Optional.ofNullable(map.get(type)).map(Evaluation::getScore).orElse(0.0);
    }

    private static String getContent(Map<EvaluationType, Evaluation> map, EvaluationType type) {
        return Optional.ofNullable(map.get(type)).map(Evaluation::getContent).orElse(null);
    }
}
