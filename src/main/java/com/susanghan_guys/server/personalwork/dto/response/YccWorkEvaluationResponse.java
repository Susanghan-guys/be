package com.susanghan_guys.server.personalwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "출품작 전체 총평 API")
public record YccWorkEvaluationResponse(
        @Schema(description = "작품 총점", example = "72")
        Integer totalScore,

        @Schema(description = "실현 가능성 총점", example = "8.4")
        double feasibilityScore,

        @Schema(description = "실현 가능성", example = "캠페인은 텀블러 꾸미기와 적립 쿠폰을 통해 지속적인 사용을 유도하는 현실적인 접근임. 카페에서의 자연스러운 참여를 통해 실행 가능성이 높음.")
        String feasibility,

        @Schema(description = "매체 선정 총점", example = "6.8")
        double mediaScore,

        @Schema(description = "매체 선정", example = "주요 매체로는 카페와 SNS가 활용되며, 텀블러 꾸미기 스티커와 적립 쿠폰이 핵심 도구로 사용됨. 이러한 매체는 젊은 층에게 친숙하고 브랜드 전략에 적합함.")
        String media,

        @Schema(description = "아젠다 선정 총점", example = "8.2")
        double agendaScore,

        @Schema(description = "아젠다 선정", example = "텀블러 사용을 통한 환경 보호라는 주제는 사회적으로 공감대를 형성할 수 있는 주제임. 텀블러의 올바른 사용 문화 확산을 목표로 하고 있음.")
        String agenda,

        @Schema(description = "영향력 총점", example = "8.7")
        double influenceScore,

        @Schema(description = "영향력", example = "캠페인은 텀블러 꾸미기와 SNS 공유를 통해 자발적인 참여를 유도하며, 사회적 영향력을 확장할 가능성이 있음. 참여자들이 환경 보호에 기여하는 느낌을 받을 수 있음.")
        String influence,

        @Schema(description = "전달력 총점", example = "6.1")
        double deliveryScore,

        @Schema(description = "전달력", example = "메시지는 '하나의 텀블러를 꾸준히 사용하자'는 명확한 슬로건을 통해 전달됨. 시각적 요소와 디자인이 감성적으로 다가와 메시지 전달 효과를 높임.")
        String delivery
) {
}
