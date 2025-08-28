package com.susanghan_guys.server.personalwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "출품작 세부 총평 API")
public record DetailEvaluationResponse(
        @Schema(description = "출품작 세부 총평")
        List<DetailEvaluation> detailEvaluations
) {

    @Builder
    @Schema(description = "출품작 세부 총평")
    public record DetailEvaluation(
            @Schema(description = "세부 총평 타입", example = "YCC_FEAS_TECH")
            String code,

            @Schema(description = "세부 총평 라벨", example = "기술적/제작적 실현 가능성")
            String label,

            @Schema(description = "세부 총평 점수", example = "9")
            Integer score,

            @Schema(description = "세부 총평 내용", example = "스티커와 적립 쿠폰을 활용한 텀블러 꾸미기 아이디어는 현재 기술로 충분히 구현 가능하며, 카페와의 협업도 현실적임. 스티커 제작과 쿠폰 시스템은 이미 널리 사용되고 있는 방식이라 기술적 한계가 없음.")
            String description
    ) {}
}
