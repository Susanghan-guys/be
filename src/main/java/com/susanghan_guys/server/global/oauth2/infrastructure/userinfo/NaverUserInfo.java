package com.susanghan_guys.server.global.oauth2.infrastructure.userinfo;

import com.susanghan_guys.server.global.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    private Map<String, Object> getResponse() {
        return (Map<String, Object>) attributes.get("response");
    }

    @Override
    public SocialLogin getProvider() {
        return SocialLogin.NAVER;
    }

    @Override
    public String providerId() {
        return (String) getResponse().get("id");
    }

    @Override
    public String getEmail() {
        return (String) getResponse().get("email");
    }

    @Override
    public String getName() {
        return (String) getResponse().get("name");
    }
}