package com.susanghan_guys.server.work.domain.type;

import lombok.Getter;

@Getter
public enum WorkType {
    DCA("DCA (FILM 제외)"),
    DCA_FILM("DCA_FILM"),
    DCA_ALL("모든 DCA 출품작 (DCA + DCA_FILM)"),
    YCC("YCC"),
    DCA_YCC("DCA (FILM 제외) + YCC");

    private final String label;

    WorkType(String label) {
        this.label = label;
    }
}
