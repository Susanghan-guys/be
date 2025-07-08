package com.susanghan_guys.server.user.application;

import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.jwt.JwtProvider;
import com.susanghan_guys.server.global.oauth2.domain.RefreshToken;
import com.susanghan_guys.server.global.oauth2.infrastructure.persistence.RefreshTokenRepository;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.dto.response.RefreshTokenResponse;
import com.susanghan_guys.server.user.infrastructure.persistence.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(String accessToken) {
        Claims claims = jwtProvider.getClaims(accessToken);
        String userId = claims.getSubject();

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        refreshTokenRepository.delete(refreshToken);
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
