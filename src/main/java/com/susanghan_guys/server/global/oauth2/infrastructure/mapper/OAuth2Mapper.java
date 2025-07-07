package com.susanghan_guys.server.global.oauth2.infrastructure.mapper;

import com.susanghan_guys.server.global.oauth2.dto.response.OAuth2Response;
import com.susanghan_guys.server.user.domain.User;

public class OAuth2Mapper {

    public static OAuth2Response toDomain(User user, String accessToken, String refreshToken) {
        return OAuth2Response.builder()
                .email(user.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
