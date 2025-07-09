package com.susanghan_guys.server.user.application;

import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import com.susanghan_guys.server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final CurrentUserProvider currentUserProvider;

    @Transactional
    public void saveUserAgreement(UserTermsRequest request) {
        User user = currentUserProvider.getCurrentUser();

        if (!request.isServiceAgreement() || !request.isUserInfoAgreement()) {
            throw new BusinessException(ErrorCode.REQUIRED_TERMS_NOT_AGREED);
        }
        user.updateUserAgreement(true, true, request.isMarketingAgreement());
    }

    @Transactional
    public void saveUserOnboarding(UserOnboardingRequest request) {
        User user = currentUserProvider.getCurrentUser();

        user.updateUserOnboarding(
                request.role(),
                request.purpose(),
                request.purposeEtc(),
                request.channel(),
                request.channelEtc()
        );
    }
}
