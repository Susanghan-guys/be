package com.susanghan_guys.server.personalwork.domain.type;

import lombok.Getter;

@Getter
public enum EvaluationType {
    TARGET_FITNESS("타겟 적합성"),
    BRAND_UNDERSTANDING("브랜드 이해도"),
    MEDIA_SELECTION("매체 선정"),
    PROBLEM_DEFINITION("문제 정의"),
    FEASIBILITY("실현 가능성"),
    STORYTELLING("스토리텔링"),
    DIRECTION("연출"),
    AGENDA_SELECTION("아젠다 선정"),
    INFLUENCE("영향력"),
    DELIVERY("전달력");

    private final String label;

    EvaluationType(String label) {
        this.label = label;
    }
}
