package com.susanghan_guys.server.oauth2.infrastructure.userinfo;

import com.susanghan_guys.server.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    private Map<String, Object> getResponseMap() {
        Object response = attributes.get("response");
        if (response instanceof Map<?, ?>) {
            return (Map<String, Object>) response;
        }
        return null;
    }

    @Override
    public String getProviderId() {
        Map<String, Object> responseMap = getResponseMap();
        return responseMap != null ? (String) responseMap.get("id") : null;
    }

    @Override
    public String getEmail() {
        Map<String, Object> responseMap = getResponseMap();
        return responseMap != null ? (String) responseMap.get("email") : null;
    }

    @Override
    public String getName() {
        Map<String, Object> responseMap = getResponseMap();
        return responseMap != null ? (String) responseMap.get("name") : null;
    }

    @Override
    public SocialLogin getProvider() {
        return SocialLogin.NAVER;
    }

    @Override
    public String getProfileImage() {
        Map<String, Object> responseMap = getResponseMap();
        return responseMap != null ? (String) responseMap.get("profile_image") : null;
    }
}