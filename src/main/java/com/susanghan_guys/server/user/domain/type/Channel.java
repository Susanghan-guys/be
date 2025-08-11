package com.susanghan_guys.server.user.domain.type;

import lombok.Getter;

@Getter
public enum Channel {
    INSTA("인스타그램"),
    SEARCH("인터넷 서칭"),
    FRIEND("지인 추천"),
    ETC("기타");

    private final String label;

    Channel(String label) {
        this.label = label;
    }
}
