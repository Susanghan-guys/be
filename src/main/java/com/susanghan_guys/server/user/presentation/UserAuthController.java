package com.susanghan_guys.server.user.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.global.jwt.JwtProvider;
import com.susanghan_guys.server.user.application.UserAuthService;
import com.susanghan_guys.server.user.dto.request.RefreshTokenRequest;
import com.susanghan_guys.server.user.dto.response.RefreshTokenResponse;
import com.susanghan_guys.server.user.presentation.swagger.UserAuthSwagger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.global.common.code.SuccessCode.USER_LOGOUT_SUCCESS;
import static com.susanghan_guys.server.global.common.code.SuccessCode.REFRESH_TOKEN_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class UserAuthController implements UserAuthSwagger {

    private final JwtProvider jwtProvider;
    private final UserAuthService userAuthService;

    @Override
    @PostMapping("/logout")
    public CommonResponse<String> logout(HttpServletRequest request) {
        userAuthService.logout(jwtProvider.resolveToken(request));
        return CommonResponse.success(USER_LOGOUT_SUCCESS, "OK");
    }

    @Override
    @PostMapping("/refresh-token")
    public CommonResponse<RefreshTokenResponse> updateRefreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        RefreshTokenResponse refreshTokenResponse = userAuthService.refreshTokenResponse(request.refreshToken());
        return CommonResponse.success(REFRESH_TOKEN_SUCCESS, refreshTokenResponse);
    }
}
