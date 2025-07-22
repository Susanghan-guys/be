package com.susanghan_guys.server.user.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.user.application.UserService;
import com.susanghan_guys.server.user.dto.request.MyPageInfoRequest;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import com.susanghan_guys.server.user.dto.request.UserWithdrawalRequest;
import com.susanghan_guys.server.user.dto.response.MyPageInfoResponse;
import com.susanghan_guys.server.user.presentation.swagger.UserSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.global.common.code.SuccessCode.*;

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

    @Override
    @GetMapping("/me")
    public CommonResponse<MyPageInfoResponse> getMyPageInfo() {
        return CommonResponse.success(USER_INFO_SUCCESS, userService.getMyPageInfo());
    }

    @Override
    @PatchMapping("/me")
    public CommonResponse<MyPageInfoResponse> updateMyPageInfo(@RequestBody @Valid MyPageInfoRequest request) {
        return CommonResponse.success(USER_INFO_UPDATE_SUCCESS, userService.updateMyPageInfo(request));
    }

    @Override
    @DeleteMapping("/me/withdrawal")
    public CommonResponse<String> withdrawUser(@RequestBody @Valid UserWithdrawalRequest request) {
        userService.withdrawUser(request);
        return CommonResponse.success(USER_WITHDRAWAL_SUCCESS, "OK");
    }
}
