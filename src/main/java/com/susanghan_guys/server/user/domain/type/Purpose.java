package com.susanghan_guys.server.user.domain.type;

import lombok.Getter;

@Getter
public enum Purpose {
    CURRENT_ANALYSIS("현업자의 수상분석"),
    AI_FEEDBACK("AI 기반 피드백"),
    TREND("출품작 트렌드 확인"),
    NEXT_CONTEST("다음 공모전 준비"),
    ETC("기타");

    private final String label;

    Purpose(String label) {
        this.label = label;
    }
}
