package com.susanghan_guys.server.personalwork.domain.type;

import lombok.Getter;

@Getter
public enum EvaluationType {
    TARGET_FITNESS("타겟 적합성", "prompt"),
    BRAND_UNDERSTANDING("브랜드 이해도", "prompt"),
    MEDIA_SELECTION("매체 선정", "prompt"),
    PROBLEM_DEFINITION("문제 정의", "prompt"),
    FEASIBILITY("실현 가능성", "prompt"),
    STORYTELLING("스토리텔링", "prompt"),
    DIRECTION("연출", "prompt"),
    AGENDA_SELECTION("아젠다 선정", "prompt"),
    INFLUENCE("영향력", "prompt"),
    DELIVERY("전달력", "prompt");

    private final String label;
    private final String prompt;

    EvaluationType(String label, String prompt) {
        this.label = label;
        this.prompt = prompt;
    }
}
