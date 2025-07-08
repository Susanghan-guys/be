package com.susanghan_guys.server.global.oauth2.application;

import com.susanghan_guys.server.global.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.global.oauth2.infrastructure.userinfo.GoogleUserInfo;
import com.susanghan_guys.server.global.security.CustomUserDetails;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.infrastructure.persistence.UserRepository;
import com.susanghan_guys.server.user.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(registrationId, attributes);

        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .orElseGet(() -> {
                    User newUser = UserMapper.toDomain(oAuth2UserInfo);
                    return userRepository.save(newUser);
                });

        return new CustomUserDetails(user, attributes);
    }

    private static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId.toUpperCase()) {
            case "GOOGLE" -> new GoogleUserInfo(attributes);
            // case "KAKAO" -> new KakaoUserInfo(attributes);
            // case "NAVER" -> new NaverUserInfo(attributes);
            default -> throw new OAuth2AuthenticationException("지원하지 않는 소셜 로그인입니다: " + registrationId);
        };
    }
}
