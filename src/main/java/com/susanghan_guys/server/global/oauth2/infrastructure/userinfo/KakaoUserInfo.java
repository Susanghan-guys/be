package com.susanghan_guys.server.global.oauth2.infrastructure.userinfo;

import com.susanghan_guys.server.global.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
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
        Map<String, Object> accountMap = (Map<String, Object>) attributes.get("kakao_account");
        return (String) accountMap.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> accountMap = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profileMap = (Map<String, Object>) accountMap.get("profile");
        return (String) profileMap.get("nickname");
    }

    @Override
    public SocialLogin getProvider() {
        return SocialLogin.KAKAO;
    }
}
