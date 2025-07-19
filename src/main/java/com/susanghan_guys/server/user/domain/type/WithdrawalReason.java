package com.susanghan_guys.server.user.domain.type;

import lombok.Getter;

@Getter
public enum WithdrawalReason {
    LOW_EFFECT("학습 효과 미비"),
    USABILITY("서비스 사용성"),
    OTHER_SERVICE("다른 서비스 사용"),
    NO_CONTEST("원하는 공모전 부재"),
    UNKNOWN("어떤 무언가"),
    SECURITY("보안 우려"),
    ETC("기타");

    private final String label;

    WithdrawalReason(String label) {
        this.label = label;
    }
}
