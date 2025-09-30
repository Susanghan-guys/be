package com.susanghan_guys.server.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.susanghan_guys.server.global.security.jwt.JwtProvider;
import com.susanghan_guys.server.oauth2.domain.RefreshToken;
import com.susanghan_guys.server.oauth2.domain.validator.RedirectValidator;
import com.susanghan_guys.server.oauth2.infrastructure.persistence.RefreshTokenRepository;
import com.susanghan_guys.server.global.security.CustomUserDetails;
import com.susanghan_guys.server.global.util.RedisUtil;
import com.susanghan_guys.server.user.domain.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ObjectMapper objectMapper;
    private final RedisUtil redisUtil;
    private final RedirectValidator redirectValidator;

    @Value("${frontend.oauth2.base-redirect-uri}")
    private String baseRedirectUri;

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

        String provider = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();

        String redirectParam = extractAndDeleteCookie(request, response, "redirect_" + provider);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(baseRedirectUri)
                .queryParam("code", tempCode);

        if (redirectParam != null && !redirectParam.isBlank()&& !redirectValidator.isAuthorized(redirectParam)) {
            builder.queryParam("redirect", redirectParam);
        }

        String callbackUri = builder.build().toUriString();

        response.sendRedirect(callbackUri);
    }

    private String extractAndDeleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (name.equals(cookie.getName())) {
                String param = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);

                Cookie expired = new Cookie(name, null);
                expired.setPath("/");
                expired.setMaxAge(0);
                response.addCookie(expired);

                return param;
            }
        }
        return null;
    }
}
