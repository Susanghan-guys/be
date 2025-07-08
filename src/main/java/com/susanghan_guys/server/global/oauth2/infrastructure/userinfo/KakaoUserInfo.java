package com.susanghan_guys.server.global.oauth2.infrastructure.userinfo;

import com.susanghan_guys.server.global.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("account_email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("profile_nickname");
    }

    @Override
    public SocialLogin getProvider() {
        return SocialLogin.KAKAO;
    }
}
