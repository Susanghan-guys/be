package com.susanghan_guys.server.global.oauth2.domain;

import com.susanghan_guys.server.user.domain.type.SocialLogin;

public interface OAuth2UserInfo {
    String getProviderId();

    String getEmail();
    String getName ();
    SocialLogin getProvider();

    String getProfileImage();
}
