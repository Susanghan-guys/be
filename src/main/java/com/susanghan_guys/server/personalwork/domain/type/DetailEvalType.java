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
    MEDIA_STRATEGY_FIT("캠페인 목적에 적합한 매체 전략 선정 여부", "Whether the chosen media type and format align with the campaign’s main objectives (awareness, engagement, brand experience, etc.).", EvaluationType.MEDIA_SELECTION),
    MEDIA_CREATIVITY("매체 활용의 창의성 및 확장성", "Whether existing media are used in innovative ways or new media opportunities are explored.", EvaluationType.MEDIA_SELECTION),
    MEDIA_CONTEXTUALITY("맥락 및 상황에 기반한 매체 활용 여부", "Whether media placement and usage are tailored to the target’s daily life, situations, and context.", EvaluationType.MEDIA_SELECTION),
    MEDIA_SYNERGY("채널 간 유기성 및 시너지 구조 (기획서 형식만)", "prompt", EvaluationType.MEDIA_SELECTION),
    MESSAGE_CLARITY("메시지 전달 방식의 직관성", "Whether the content delivered through media is simple, instantly understandable, and prompts direct reactions from the target.", EvaluationType.MEDIA_SELECTION),

    // DCA - 문제 정의
    PROBLEM_CLARITY("문제 정의의 명확성과 설득력", "prompt", EvaluationType.PROBLEM_DEFINITION),
    DATA_ALIGNMENT("자료와 문제 정의 간의 일관성 연계성", "prompt", EvaluationType.PROBLEM_DEFINITION),
    TASK_FIDELITY("과제 해석의 충실성", "prompt", EvaluationType.PROBLEM_DEFINITION),
    PROBLEM_PRACTICALITY("문제 정의의 실질성", "prompt", EvaluationType.PROBLEM_DEFINITION),
    PROBLEM_DIFFERENTIATION("문제 정의의 차별성", "prompt", EvaluationType.PROBLEM_DEFINITION),

    // DCA - 실현 가능성
    FEAS_TECH("기술적/제작적 실현 가능성", "Whether the campaign idea can be implemented with existing technology.", EvaluationType.FEASIBILITY),
    FEAS_CONCRETENESS("실행의 구체성", "Whether the campaign idea is logically connected to the target’s characteristics and effectively reflects their motivations, emotions, and behaviors", EvaluationType.FEASIBILITY),
    FEAS_EFFECT_PERSUASION("기대 효과의 설득력", "Whether the expected outcomes and positive changes from the campaign are realistic and convincing.", EvaluationType.FEASIBILITY),
    FEAS_ASSUMPTION_VALIDITY("전제의 합리성", "Whether the conditions required for the campaign are realistic, not overly idealized or impractical.", EvaluationType.FEASIBILITY),
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
    FEAS_SIMPLE_INTERVENTION("간단한 개입으로 연결 가능한 구조", "Whether the campaign can be easily understood and joined by individuals through a simple and direct participation structure.", EvaluationType.FEASIBILITY),

    // YCC - 매체 선정
    MEDIA_EASE("실행 용이성", "Whether the media plan can be realistically executed within budget and technical constraints.", EvaluationType.MEDIA_SELECTION),

    // YCC - 아젠다 선정
    AGENDA_DAILY_LIFE("생활 밀착도", "Whether the campaign insightfully reframes everyday inconveniences or risks into a relatable issue that feels urgent and meaningful, rather than merely describing common problems.", EvaluationType.AGENDA_SELECTION),
    AGENDA_SENSITIVITY("민감도 회피 및 긍정성 유지", "Whether the campaign interprets sensitive social issues in a constructive way, avoiding fatigue or polarization, and transforms them into a light or hopeful message that encourages engagement.", EvaluationType.AGENDA_SELECTION),
    AGENDA_SHIFT("인식 전환 유도", "Whether the campaign’s core concept or execution provides a fresh perspective that can shift perceptions or behaviors, not only by presenting information but by reframing the issue in a new light.", EvaluationType.AGENDA_SELECTION),
    AGENDA_PUBLIC_SPREAD("공공성 기반의 확산 가능성", "Whether the campaign identifies a theme with inherent public value, extending beyond personal matters, and transforms it into a socially shareable or culturally resonant agenda.", EvaluationType.AGENDA_SELECTION),
    AGENDA_CLARITY("문제의 명확성", "Whether the campaign defines the core problem with clarity and interpretive sharpness, making the social issue immediately recognizable through a strong concept rather than vague discourse.", EvaluationType.AGENDA_SELECTION),

    // YCC - 영향력
    IMPACT_PARTICIPATION("참여 유도력", "Whether the campaign encourages spontaneous and voluntary participation from the target, going beyond passive reception.", EvaluationType.INFLUENCE),
    IMPACT_MEASURABLE("성과 측정 가능성", "Whether expected outcomes are defined with specific KPIs or data points, making them measurable and analyzable after execution.", EvaluationType.INFLUENCE),
    IMPACT_SCALABILITY("확장성", "Whether the campaign can expand naturally across different contexts, targets, and channels without losing its core message.", EvaluationType.INFLUENCE),
    IMPACT_EFFECTIVENESS("실효성", "Whether the campaign contributes practically to solving the issue, moving beyond symbolic gestures or remaining at the conceptual stage.", EvaluationType.INFLUENCE),
    IMPACT_BUZZ("화제성", "Whether the campaign generates public buzz or cultural resonance that can spread widely and organically across media and communities.", EvaluationType.INFLUENCE),

    // YCC - 전달력
    DELIVERY_STORY("스토리텔링", "Whether the campaign goes beyond information delivery and immerses the target in a compelling story-like flow where the process itself feels experiential and naturally engaging.", EvaluationType.DELIVERY),
    DELIVERY_EMPATHY("공감 유도", "Whether the campaign’s message resonates emotionally, allowing the audience to intuitively feel the seriousness or relevance of the issue, especially through familiar or personal elements that trigger empathy.", EvaluationType.DELIVERY),
    DELIVERY_CRAFT("시각, 언어 표현의 완성도", "Whether visual elements (e.g., objects, symbols, design) and verbal expressions (e.g., slogans, copy) work together with clarity and polish, reinforcing the message so it is easily memorable and impactful.", EvaluationType.DELIVERY),
    DELIVERY_LOGIC("논리성", "Whether the message is presented with logical consistency, connecting the problem, insights, and solution clearly and persuasively so that the flow feels coherent and evidence-based.", EvaluationType.DELIVERY),
    DELIVERY_NAMING("직관적인 네이밍", "Whether slogans, taglines, or names are intuitive, concise, and memorable enough to instantly convey the campaign’s core idea and leave a strong impression in a short moment.", EvaluationType.DELIVERY);

    private final String label;
    private final String prompt;
    private final EvaluationType type;

    DetailEvalType(String label, String prompt, EvaluationType type) {
        this.label = label;
        this.prompt = prompt;
        this.type = type;
    }
}
