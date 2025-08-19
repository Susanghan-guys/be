package com.susanghan_guys.server.personalwork.domain.type;

import com.susanghan_guys.server.work.domain.type.WorkType;
import lombok.Getter;

import static com.susanghan_guys.server.work.domain.type.WorkType.*;

@Getter
public enum EvaluationType {
    TARGET_FITNESS("타겟 적합성", WorkType.DCA_ALL),
    BRAND_UNDERSTANDING("브랜드 이해도", WorkType.DCA_ALL),
    MEDIA_SELECTION("매체 선정", WorkType.DCA_YCC),
    PROBLEM_DEFINITION("문제 정의", DCA_ALL),
    FEASIBILITY("실현 가능성", WorkType.DCA_YCC),
    STORYTELLING("스토리텔링", DCA_FILM),
    DIRECTION("연출", DCA_FILM),
    AGENDA_SELECTION("아젠다 선정", WorkType.YCC),
    INFLUENCE("영향력", WorkType.YCC),
    DELIVERY("전달력", WorkType.YCC);

    private final String label;
    private final WorkType type;

    EvaluationType(String label, WorkType type) {
        this.label = label;
        this.type = type;
    }
}
