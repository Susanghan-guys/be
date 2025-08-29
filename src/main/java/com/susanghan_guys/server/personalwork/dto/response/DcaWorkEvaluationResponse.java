package com.susanghan_guys.server.personalwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "출품작 전체 총평 API")
public record DcaWorkEvaluationResponse(

        @Schema(description = "작품 총점", example = "72")
        Integer totalScore,

        @Schema(description = "타겟 적합성 총점", example = "8.1")
        double targetScore,
        
        @Schema(description = "타겟 적합성", example = "캠페인은 1020세대를 주요 타겟으로 설정하여 그들의 학업 스트레스와 시간 관리 문제를 해결하고자 함. 타이머와 빼빼로를 결합한 패키지를 통해 감성적 연결을 강화하려는 시도가 돋보임.")
        String  target,

        @Schema(description = "브랜드 이해도 총점", example = "8.4")
        double brandScore,

        @Schema(description = "브랜드 이해도", example = "빼빼로의 본질적 가치인 '나눔/연결의 가치'를 유지하면서도 새로운 패키지 디자인을 통해 신선함을 전달하고자 함. 브랜드의 긍정적 이미지를 강화하려는 노력이 엿보임.")
        String  brand,

        @Schema(description = "매체 선정 총점", example = "8.0")
        double mediaScore,
        
        @Schema(description = "매체 선정", example = "타이머와 결합된 빼빼로 패키지를 통해 직접적인 메시지를 전달하며, 학생들에게 실질적인 도움을 줄 수 있는 매체로 활용됨. 이 패키지는 소비자들이 빼빼로를 통해 자연스럽게 휴식을 취할 수 있도록 유도함.")
        String  media,

        @Schema(description = "문제 정의 총점", example = "7.9")
        double problemScore,

        @Schema(description = "문제 정의", example = "1020세대의 학업 스트레스와 시간 관리 문제를 해결하고자 하는 캠페인으로, 기존 이미지와 결합하여 새로운 경험을 제공함. 이를 통해 빼빼로데이의 참여율을 높이고자 하는 문제를 구체적으로 다룸.")
        String  problem,

        @Schema(description = "실현 가능성 총점", example = "9.2")
        double feasibilityScore,

        @Schema(description = "실현 가능성", example = "타이머와 결합된 패키지는 실현 가능성이 높으며, 학생들에게 실질적인 도움을 줄 수 있는 아이디어임. 빼빼로를 통해 자연스럽게 휴식을 취할 수 있도록 유도하는 점에서 기대 효과가 큼.")
        String  feasibility
) {}