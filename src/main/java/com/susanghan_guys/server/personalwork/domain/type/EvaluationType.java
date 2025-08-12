package com.susanghan_guys.server.personalwork.domain.type;

import com.susanghan_guys.server.work.domain.type.WorkType;
import lombok.Getter;

@Getter
public enum EvaluationType {
    TARGET_FITNESS("타겟 적합성", "prompt", WorkType.DCA_ALL),
    BRAND_UNDERSTANDING("브랜드 이해도", "prompt", WorkType.DCA_ALL),
    MEDIA_SELECTION("매체 선정", "prompt", WorkType.DCA_YCC),
    PROBLEM_DEFINITION("문제 정의", "prompt", WorkType.DCA_ALL),
    FEASIBILITY("실현 가능성", "prompt", WorkType.DCA_YCC),
    STORYTELLING("스토리텔링", "prompt", WorkType.DCA_FILM),
    DIRECTION("연출", "prompt", WorkType.DCA_FILM),
    AGENDA_SELECTION("아젠다 선정", "prompt", WorkType.YCC),
    INFLUENCE("영향력", "prompt", WorkType.YCC),
    DELIVERY("전달력", "prompt", WorkType.YCC);

    private final String label;
    private final String prompt;
    private final WorkType type;

    EvaluationType(String label, String prompt, WorkType type) {
        this.label = label;
        this.prompt = prompt;
        this.type = type;
    }
}
