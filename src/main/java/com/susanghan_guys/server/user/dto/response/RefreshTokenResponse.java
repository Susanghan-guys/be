package com.susanghan_guys.server.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "refreshToken 재발급")
public record RefreshTokenResponse(
        @Schema(
                description = "재발급된 accessToken",
                example = "eyJh.eqi57ht6K"
        )
        String accessToken,

        @Schema(
                description = "재발급된 refreshToken",
                example = "eyJh.eqi57hr4K"
        )
        String refreshToken
) {
}
