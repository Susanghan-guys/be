package com.susanghan_guys.server.global.oauth2.infrastructure.userinfo;

import com.susanghan_guys.server.global.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    @Override
    public String providerId() {
        return (String) attributes.get("providerId");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public SocialLogin getProvider() {
        return SocialLogin.GOOGLE;
    }
}
