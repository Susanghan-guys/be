package com.susanghan_guys.server.user.application;

import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.jwt.JwtProvider;
import com.susanghan_guys.server.global.oauth2.domain.RefreshToken;
import com.susanghan_guys.server.global.oauth2.infrastructure.persistence.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(String accessToken) {
        Claims claims = jwtProvider.getClaims(accessToken);
        String userId = claims.getSubject();

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        refreshTokenRepository.delete(refreshToken);
    }
}
