package com.susanghan_guys.server.oauth2.application;

import com.susanghan_guys.server.oauth2.domain.OAuth2UserInfo;
import com.susanghan_guys.server.oauth2.infrastructure.userinfo.GoogleUserInfo;
import com.susanghan_guys.server.oauth2.infrastructure.userinfo.NaverUserInfo;
import com.susanghan_guys.server.oauth2.infrastructure.userinfo.KakaoUserInfo;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        log.info("🌐 OAuth2 provider: {}", registrationId);
        log.info("📦 Received attributes: {}", attributes);

        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(registrationId, attributes);
        String email = oAuth2UserInfo.getEmail();

        if (email == null || email.isBlank()) {
            log.error("❌ 소셜 로그인 실패 - 이메일이 존재하지 않음. attributes = {}", attributes);
            throw new OAuth2AuthenticationException("소셜 로그인에 이메일 정보가 없습니다.");
        }
        boolean isSignUp;
        User user;

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            isSignUp = false;
        } else {
            user = userRepository.save(UserMapper.toDomain(oAuth2UserInfo));
            isSignUp = true;
        }
        Map<String, Object> updatedAttributes = new HashMap<>(attributes);
        updatedAttributes.put("isSignUp", isSignUp);

        return new CustomUserDetails(user, updatedAttributes);
    }

    private static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId.toUpperCase()) {
            case "GOOGLE" -> new GoogleUserInfo(attributes);
            case "NAVER" -> new NaverUserInfo(attributes);
            case "KAKAO" -> new KakaoUserInfo(attributes);
            default -> throw new OAuth2AuthenticationException("지원하지 않는 소셜 로그인입니다: " + registrationId);
        };
    }
}
