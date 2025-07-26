package com.susanghan_guys.server.user.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.dto.request.MyPageInfoRequest;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.dto.request.UserWithdrawalRequest;
import com.susanghan_guys.server.user.dto.response.MyPageInfoResponse;
import com.susanghan_guys.server.user.infrastructure.persistence.UserRepository;
import com.susanghan_guys.server.withdrawal.application.ReasonService;
import com.susanghan_guys.server.user.application.validator.UserValidator;
import com.susanghan_guys.server.withdrawal.application.validator.ReasonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final CurrentUserProvider currentUserProvider;
    private final UserValidator userValidator;
    private final UserAuthService userAuthService;
    private final UserRepository userRepository;
    private final ReasonService reasonService;
    private final ReasonValidator reasonValidator;

    @Transactional
    public void saveUserAgreement(UserTermsRequest request) {
        User user = currentUserProvider.getCurrentUser();

        userValidator.validateUserAgreement(request);

        user.updateUserAgreement(true, true, request.isMarketingAgreement());
    }

    @Transactional
    public void saveUserOnboarding(UserOnboardingRequest request) {
        User user = currentUserProvider.getCurrentUser();

        userValidator.validateUserOnboarding(request);

        user.updateUserOnboarding(
                true,
                request.name(),
                request.role(),
                request.purpose(),
                request.purposeEtc(),
                request.channel(),
                request.channelEtc()
        );
    }

    @Transactional
    public MyPageInfoResponse updateMyPageInfo(MyPageInfoRequest request) {
        User user = currentUserProvider.getCurrentUser();

        user.updateUserInfo(request.name());

        return MyPageInfoResponse.from(user);
    }

    @Transactional
    public void withdrawUser(UserWithdrawalRequest request) {
        User user = currentUserProvider.getCurrentUser();

        reasonValidator.validateWithdrawalReason(request);

        reasonService.saveWithdrawalReason(
                user.getEmail(),
                request.withdrawalReasons(),
                request.etc()
        );
        userRepository.delete(user);
    }

    public MyPageInfoResponse getMyPageInfo() {
        return MyPageInfoResponse.from(currentUserProvider.getCurrentUser());
    }
}
