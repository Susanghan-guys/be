package com.susanghan_guys.server.user.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.jwt.JwtProvider;
import com.susanghan_guys.server.global.oauth2.application.TokenBlackListService;
import com.susanghan_guys.server.global.oauth2.domain.RefreshToken;
import com.susanghan_guys.server.global.oauth2.infrastructure.persistence.RefreshTokenRepository;
import com.susanghan_guys.server.global.util.RedisUtil;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.dto.response.RefreshTokenResponse;
import com.susanghan_guys.server.user.dto.response.ExchangeTokenResponse;
import com.susanghan_guys.server.user.infrastructure.persistence.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenBlackListService tokenBlackListService;
    private final ObjectMapper objectMapper;
    private final RedisUtil redisUtil;

    public ExchangeTokenResponse exchangeToken(String code) {
        String key = "auth:" + code;
        String json = redisUtil.getValue(key);

        if (json == null) {
            throw new BusinessException(ErrorCode.INVALID_AUTH_CODE);
        }

        try {
            Map<String, String> data = objectMapper.readValue(json, new TypeReference<>() {});
            String accessToken = data.get("accessToken");
            String refreshToken = data.get("refreshToken");
            boolean isSignUp = Boolean.parseBoolean(data.get("isSignUp"));

            Claims claims = jwtProvider.getClaims(accessToken);
            String userId = claims.getSubject();

            User user = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

            redisUtil.deleteValue(key);

            return ExchangeTokenResponse.of(accessToken, refreshToken, user, isSignUp);
        } catch (JsonProcessingException e) {
            redisUtil.deleteValue(key);
            throw new BusinessException(ErrorCode.TOKEN_PARSE_FAILED);
        }
    }

    @Transactional
    public void logout(String accessToken) {
        Claims claims = jwtProvider.getClaims(accessToken);
        String userId = claims.getSubject();

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        refreshTokenRepository.delete(refreshToken);

        long expiration = claims.getExpiration().getTime() - System.currentTimeMillis();

        if (expiration <= 0) {
            expiration = 1000;
        }
        tokenBlackListService.addBlackList(accessToken, "logout", Duration.ofMillis(expiration));
    }

    @Transactional
    public RefreshTokenResponse refreshTokenResponse(String refreshToken) {
        jwtProvider.validateToken(refreshToken);
        Claims claims = jwtProvider.getClaims(refreshToken);
        String userId = claims.getSubject();

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RefreshToken savedToken = refreshTokenRepository.findByUserId(user.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        String newAccessToken = jwtProvider.createAccessToken(user.getId());
        String newRefreshToken = jwtProvider.createRefreshToken(user.getId());

        savedToken.updateRefreshToken(newRefreshToken);
        refreshTokenRepository.save(savedToken);

        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }
}
