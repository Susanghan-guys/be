package com.susanghan_guys.server.user.infrastructure.mapper;

import com.susanghan_guys.server.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.user.domain.User;

public class UserMapper {

    public static User toEntity(OAuth2UserInfo oAuth2UserInfo) {
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getName())
                .profileImage(oAuth2UserInfo.getProfileImage())
                .providerId(oAuth2UserInfo.getProviderId())
                .socialLogin(oAuth2UserInfo.getProvider())
                .build();
    }
}
