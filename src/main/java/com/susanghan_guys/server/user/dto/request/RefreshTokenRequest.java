package com.susanghan_guys.server.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "refreshToken 재발급")
public record RefreshTokenRequest(
        @Schema(
                description = "만료된 refreshToken",
                example = "eyJh.eqi57hK"
        )
        String refreshToken
) {
}
