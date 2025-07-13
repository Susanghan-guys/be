package com.susanghan_guys.server.user.dto.response;

import com.susanghan_guys.server.user.domain.User;
import lombok.Builder;

@Builder
public record ExchangeTokenResponse(
        String accessToken,
        String refreshToken,
        Long userId,
        String name,
        String email,
        boolean isSignUp,
        boolean isOnboarded
) {
    public static ExchangeTokenResponse of(
            String accessToken,
            String refreshToken,
            User user,
            boolean isSignUp
    ) {
        return ExchangeTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isSignUp(isSignUp)
                .isOnboarded(user.isOnboarded())
                .build();
    }
}
