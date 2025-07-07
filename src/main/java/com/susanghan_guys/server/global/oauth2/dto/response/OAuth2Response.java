package com.susanghan_guys.server.global.oauth2.dto.response;

import lombok.Builder;

@Builder
public record OAuth2Response(
        String email,
        String accessToken,
        String refreshToken
) {
}
