package com.susanghan_guys.server.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.susanghan_guys.server.global.security.jwt.JwtProvider;
import com.susanghan_guys.server.oauth2.domain.RefreshToken;
import com.susanghan_guys.server.oauth2.infrastructure.persistence.RefreshTokenRepository;
import com.susanghan_guys.server.global.security.CustomUserDetails;
import com.susanghan_guys.server.global.util.RedisUtil;
import com.susanghan_guys.server.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ObjectMapper objectMapper;
    private final RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser();

        Map<String, Object> attributes = customUserDetails.getAttributes();
        boolean isSignUp = Boolean.parseBoolean(
                String.valueOf(attributes.getOrDefault("isSignUp", "false"))
        );

        String accessToken = jwtProvider.createAccessToken(user.getId());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));

        String tempCode = UUID.randomUUID().toString();

        redisUtil.setValue("auth:" + tempCode,
                objectMapper.writeValueAsString(Map.of(
                        "accessToken", accessToken,
                        "refreshToken", refreshToken,
                        "isSignUp", String.valueOf(isSignUp)
                )), 1000 * 60L);

        String callbackUri = getRedirectUri(request, tempCode);
        response.sendRedirect(callbackUri);
    }

    private String getRedirectUri(HttpServletRequest request, String tempCode) {
        String origin = request.getHeader("Origin");
        String redirectUri;

        if (origin != null && origin.contains("localhost")) {
            redirectUri = "http://localhost:3000/oauth/callback?code=";
        } else {
            redirectUri = "https://www.soosanghan.site/oauth/callback?code=";
        }

        return redirectUri + tempCode;
    }
}
