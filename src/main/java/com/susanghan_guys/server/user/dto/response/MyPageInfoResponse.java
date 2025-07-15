package com.susanghan_guys.server.user.dto.response;

import com.susanghan_guys.server.user.domain.User;
import lombok.Builder;

@Builder
public record MyPageInfoResponse(
        String name,
        String email,
        String profileImage
) {
    public static MyPageInfoResponse from(User user) {
        return MyPageInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .build();
    }
}
