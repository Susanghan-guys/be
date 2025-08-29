package com.susanghan_guys.server.personalwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "브리프 해석 조회 API")
public record DcaBriefEvaluationResponse(

        @Schema(
                description = "브리프 해석",
                example = "브랜드의 핵심 목표는 영타겟의 감성적 연결 및 선호도 제고이다. 이 캠페인은 타이머와 빼빼로를 결합하여 휴식과 나눔의 순간을 강조함으로써 이러한 목표를 반영하고 있다."
        )
        String interpretation,

        @Schema(
                description = "반영 일관성",
                example = "캠페인은 타이머와 빼빼로를 결합하여 '잠시 톡! 끊어가도 괜찮아'라는 메시지를 통해 영타겟에게 휴식의 중요성을 전달하고 있다. 이는 브랜드의 본질적 가치인 '나눔/연결의 가치'를 유지하면서도 새로운 경험을 제공하려는 시도이다. 또한, 패키지 디자인과 메시지 전달 방식이 젊은 층의 일상과 잘 맞아떨어져 자발적 참여를 유도하고 있다. 이러한 요소들은 브랜드의 감성적 연결을 강화하는 데 일조하고 있다."
        )
        String consistency,

        @Schema(
                description = "보완점",
                example = "타이머와의 결합이 신선하지만, 빼빼로 자체의 매력보다는 부가적인 요소에 의존하는 경향이 있어 브랜드의 본질적 가치와의 직접적 연계가 약할 수 있다."
        )
        String weakness
) {
}
