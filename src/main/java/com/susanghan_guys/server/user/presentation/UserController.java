package com.susanghan_guys.server.user.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.user.application.UserService;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import com.susanghan_guys.server.user.presentation.swagger.UserSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.global.common.code.SuccessCode.USER_AGREEMENT_SUCCESS;
import static com.susanghan_guys.server.global.common.code.SuccessCode.USER_ONBOARDING_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController implements UserSwagger {

    private final UserService userService;

    @Override
    @PostMapping("/agreement")
    public CommonResponse<String> saveUserAgreement(@RequestBody @Valid UserTermsRequest request) {
        userService.saveUserAgreement(request);
        return CommonResponse.success(USER_AGREEMENT_SUCCESS, "OK");
    }

    @Override
    @PostMapping("/onboarding")
    public CommonResponse<String> saveUserOnboarding(@RequestBody @Valid UserOnboardingRequest request) {
        userService.saveUserOnboarding(request);
        return CommonResponse.success(USER_ONBOARDING_SUCCESS, "OK");
    }
}
