package com.susanghan_guys.server.user.dto.response;

import com.susanghan_guys.server.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "사용자 정보 조회 및 수정")
public record MyPageInfoResponse(
        @Schema(description = "사용자 이름", example = "주정빈")
        String name,

        @Schema(description = "사용자 이메일", example = "yui2507@naver.com")
        String email,

        @Schema(description = "사용자 프로필 이미지", example = "https://kakao...")
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
