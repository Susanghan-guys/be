package com.susanghan_guys.server.user.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.global.jwt.JwtProvider;
import com.susanghan_guys.server.user.application.UserAuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.global.common.code.SuccessCode.USER_LOGOUT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class UserAuthController {

    private final JwtProvider jwtProvider;
    private final UserAuthService userAuthService;

    @PostMapping("/logout")
    public CommonResponse<String> logout(HttpServletRequest request) {
        userAuthService.logout(jwtProvider.resolveToken(request));
        return CommonResponse.success(USER_LOGOUT_SUCCESS, "OK");
    }
}
