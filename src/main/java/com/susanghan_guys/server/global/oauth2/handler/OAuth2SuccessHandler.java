package com.susanghan_guys.server.global.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.susanghan_guys.server.global.jwt.JwtProvider;
import com.susanghan_guys.server.global.oauth2.domain.RefreshToken;
import com.susanghan_guys.server.global.oauth2.infrastructure.persistence.RefreshTokenRepository;
import com.susanghan_guys.server.global.security.CustomUserDetails;
import com.susanghan_guys.server.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser();

        String accessToken = jwtProvider.createAccessToken(user.getId());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));

        Map<String, Object> tokens = Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getWriter(), tokens);
    }
}
