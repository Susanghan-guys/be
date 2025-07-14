package com.susanghan_guys.server.user.dto.response;

import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import lombok.Builder;

@Builder
public record ExchangeTokenResponse(
        String accessToken,
        String refreshToken,
        Long userId,
        String name,
        String email,
        String profileImage,
        SocialLogin socialLogin,
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
                .profileImage(user.getProfileImage())
                .socialLogin(user.getSocialLogin())
                .isSignUp(isSignUp)
                .isOnboarded(user.isOnboarded())
                .build();
    }
}
