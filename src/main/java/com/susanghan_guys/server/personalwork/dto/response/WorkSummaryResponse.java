package com.susanghan_guys.server.personalwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "출품작 요약 생성 API")
public record WorkSummaryResponse(
        @Schema(
                description = "Target",
                example = "정기 후원 참여율이 낮은 20~39세 MZ세대. 공감보다 자기 연결감을 중시하며, 개인의 가치에 기반한 메시지에 반응한다."
        )
        String target,

        @Schema(
                description = "Insight",
                example = "2030세대는 자신과 닮았다고 느끼는 대상에게 감정적으로 더 쉽게 연결되며, ‘투영’을 통해 연대감을 느낀다."
        )
        String insight,

        @Schema(
                description = "Solution",
                example = "수혜자에게 후원자의 어린 시절을 투영하게 하여, 정서적 연결을 통해 기부 행동으로 자연스럽게 이어지도록 유도한다."
        )
        String solution
) {
}
