package com.susanghan_guys.server.personalwork.domain.type;

import lombok.Getter;

@Getter
public enum DetailEvalType {
    // DCA - 타겟 적합성
    TARGET_INSIGHT("타겟 인사이트 도출 여부", "prompt", EvaluationType.TARGET_FITNESS),
    TARGET_LINK("아이디어의 타겟 연계성", "prompt", EvaluationType.TARGET_FITNESS),
    TARGET_BEHAVIORAL("행동 유도 전략의 타겟화 여부", "prompt", EvaluationType.TARGET_FITNESS),
    TARGET_TOUCH_POINT("타겟과의 접점 고려 여부", "prompt", EvaluationType.TARGET_FITNESS),
    TARGET_LIFESTYLE("타겟의 라이프스타일과의 접점 여부", "prompt", EvaluationType.TARGET_FITNESS),

    // DCA - 브랜드 이해도
    BRAND_INTENT("브랜드 의도성 파악", "prompt", EvaluationType.BRAND_UNDERSTANDING),
    BRAND_ASSET("브랜드 고유 자산 반영 여부", "prompt", EvaluationType.BRAND_UNDERSTANDING),
    BRAND_ONLY_IDEA("해당 브랜드만이 가능한 아이디어인지 여부", "prompt", EvaluationType.BRAND_UNDERSTANDING),
    CATEGORY_UNDERSTANDING("브랜드가 속한 카테고리에 대한 이해", "prompt", EvaluationType.BRAND_UNDERSTANDING),
    MARKET_TIMELINESS("시장 및 브랜드 시의성 반영 여부", "prompt", EvaluationType.BRAND_UNDERSTANDING),

    // DCA - 매체 선정
    MEDIA_STRATEGY_FIT("캠페인 목적에 적합한 매체 전략 선정 여부", "prompt", EvaluationType.MEDIA_SELECTION),
    MEDIA_CREATIVITY("매체 활용의 창의성 및 확장성", "prompt", EvaluationType.MEDIA_SELECTION),
    MEDIA_CONTEXTUALITY("맥락 및 상황에 기반한 매체 활용 여부", "prompt", EvaluationType.MEDIA_SELECTION),
    MEDIA_SYNERGY("채널 간 유기성 및 시너지 구조 (기획서 형식만)", "prompt", EvaluationType.MEDIA_SELECTION),
    MESSAGE_CLARITY("메시지 전달 방식의 직관성", "prompt", EvaluationType.MEDIA_SELECTION),

    // DCA - 문제 정의
    PROBLEM_CLARITY("문제 정의의 명확성과 설득력", "prompt", EvaluationType.PROBLEM_DEFINITION),
    DATA_ALIGNMENT("자료와 문제 정의 간의 일관성 연계성", "prompt", EvaluationType.PROBLEM_DEFINITION),
    TASK_FIDELITY("과제 해석의 충실성", "prompt", EvaluationType.PROBLEM_DEFINITION),
    PROBLEM_PRACTICALITY("문제 정의의 실질성", "prompt", EvaluationType.PROBLEM_DEFINITION),
    PROBLEM_DIFFERENTIATION("문제 정의의 차별성", "prompt", EvaluationType.PROBLEM_DEFINITION),

    // DCA - 실현 가능성
    FEAS_TECH("기술적/제작적 실현 가능성", "prompt", EvaluationType.FEASIBILITY),
    FEAS_CONCRETENESS("실행의 구체성", "prompt", EvaluationType.FEASIBILITY),
    FEAS_EFFECT_PERSUASION("기대 효과의 설득력", "prompt", EvaluationType.FEASIBILITY),
    FEAS_ASSUMPTION_VALIDITY("전제의 합리성", "prompt", EvaluationType.FEASIBILITY),
    FEAS_VISUAL_PLAN("실행 방안의 시각적 표현", "prompt", EvaluationType.FEASIBILITY),

    // DCA_FILM - 스토리텔링
    ALIGNMENT_WITH_INTENT("기획 의도 일치 여부", "prompt", EvaluationType.STORYTELLING),
    MESSAGE_DELIVERY("메시지의 전달력", "prompt", EvaluationType.STORYTELLING),
    TONE_AND_MANNER("톤앤매너", "prompt", EvaluationType.STORYTELLING),
    HOOKING("후킹", "prompt", EvaluationType.STORYTELLING),
    CALL_TO_ACTION("행동 유도", "prompt", EvaluationType.STORYTELLING),

    // DCA_FILM - 연출
    VISUAL_CONSISTENCY("시각적 일관성과 완성도", "prompt", EvaluationType.DIRECTION),
    MESSAGE_CENTRIC_DIRECTION("메시지 중심 연출", "prompt", EvaluationType.DIRECTION),
    STORYTELLING_DIRECTION("스토리텔링 연출 적합도", "prompt", EvaluationType.DIRECTION),
    TARGET_VIEWING_CONTEXT("타겟의 시청 맥락", "prompt", EvaluationType.DIRECTION),
    EMOTIONAL_ENGAGEMENT("감정적 몰입 유도", "prompt", EvaluationType.DIRECTION),

    // YCC - 실현 가능성
    FEAS_SIMPLE_INTERVENTION("간단한 개입으로 연결 가능한 구조", "prompt", EvaluationType.FEASIBILITY),

    // YCC - 매체 선정
    MEDIA_EASE("실행 용이성", "prompt", EvaluationType.MEDIA_SELECTION),

    // YCC - 아젠다 선정
    AGENDA_DAILY_LIFE("생활 밀착도", "prompt", EvaluationType.AGENDA_SELECTION),
    AGENDA_SENSITIVITY("민감도 회피 및 긍정성 유지", "prompt", EvaluationType.AGENDA_SELECTION),
    AGENDA_SHIFT("인식 전환 유도", "prompt", EvaluationType.AGENDA_SELECTION),
    AGENDA_PUBLIC_SPREAD("공공성 기반의 확산 가능성", "prompt", EvaluationType.AGENDA_SELECTION),
    AGENDA_CLARITY("문제의 명확성", "prompt", EvaluationType.AGENDA_SELECTION),

    // YCC - 영향력
    IMPACT_PARTICIPATION("참여 유도력", "prompt", EvaluationType.INFLUENCE),
    IMPACT_MEASURABLE("성과 측정 가능성", "prompt", EvaluationType.INFLUENCE),
    IMPACT_SCALABILITY("확장성", "prompt", EvaluationType.INFLUENCE),
    IMPACT_EFFECTIVENESS("실효성", "prompt", EvaluationType.INFLUENCE),
    IMPACT_BUZZ("화제성", "prompt", EvaluationType.INFLUENCE),

    // YCC - 전달력
    DELIVERY_STORY("스토리텔링", "prompt", EvaluationType.DELIVERY),
    DELIVERY_EMPATHY("공감 유도", "prompt", EvaluationType.DELIVERY),
    DELIVERY_CRAFT("시각, 언어 표현의 완성도", "prompt", EvaluationType.DELIVERY),
    DELIVERY_LOGIC("논리성", "prompt", EvaluationType.DELIVERY),
    DELIVERY_NAMING("직관적인 네이밍", "prompt", EvaluationType.DELIVERY);

    private final String label;
    private final String prompt;
    private final EvaluationType type;

    DetailEvalType(String label, String prompt, EvaluationType type) {
        this.label = label;
        this.prompt = prompt;
        this.type = type;
    }
}
