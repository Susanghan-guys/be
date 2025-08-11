package com.susanghan_guys.server.personalwork.domain.type;

import lombok.Getter;

@Getter
public enum EvaluationType {
    TARGET_FITNESS("prompt"),
    BRAND_UNDERSTANDING("prompt"),
    MEDIA_SELECTION("prompt"),
    PROBLEM_DEFINITION("prompt"),
    FEASIBILITY("prompt"),
    AGENDA_SELECTION("prompt"),
    INFLUENCE("prompt"),
    DELIVERY("prompt");

    private final String prompt;

    EvaluationType(String prompt) {
        this.prompt = prompt;
    }
}
