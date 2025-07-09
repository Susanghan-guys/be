package com.susanghan_guys.server.user.dto.request;

import com.susanghan_guys.server.user.domain.type.Channel;
import com.susanghan_guys.server.user.domain.type.Purpose;
import com.susanghan_guys.server.user.domain.type.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "사용자 온보딩")
public record UserOnboardingRequest(
        @NotBlank
        @Schema(
                description = "주로 맡는 역할",
                example = "STRUCTURE, DESIGN"
        )
        List<Role> role,

        @NotBlank
        @Schema(
                description = "수녀들 활용 목적",
                example = "AI_FEEDBACK"
        )
        Purpose purpose,

        @Schema(
                description = "수녀들 활용 목적 - 기타일 경우, 작성",
                example = "좋은 곳에 쓰려구용"
        )
        String purposeEtc,

        @NotBlank
        @Schema(
                description = "수녀들 알게 된 경로",
                example = "INSTA"
        )
        Channel channel,

        @Schema(
                description = "수녀들 알게 된 경로 - 기타일 경우, 작성",
                example = "친구 추천"
        )
        String channelEtc
) {
}
