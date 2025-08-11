package com.susanghan_guys.server.user.domain.type;

import lombok.Getter;

@Getter
public enum Role {
    STRUCTURE("논리 구조 설계"),
    IDEATION("아이데이션"),
    PRESENTATION("발표"),
    RESEARCH("자료조사"),
    CONCEPTING("컨셉 도출"),
    DESIGN("디자인");

    private final String label;

    Role(String label) {
        this.label = label;
    }
}
