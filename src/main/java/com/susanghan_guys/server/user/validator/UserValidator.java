package com.susanghan_guys.server.user.validator;

import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.domain.type.Channel;
import com.susanghan_guys.server.user.domain.type.Purpose;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateUserAgreement(UserTermsRequest request) {
        if (!request.isServiceAgreement() || !request.isUserInfoAgreement()) {
            throw new BusinessException(ErrorCode.REQUIRED_TERMS_NOT_AGREED);
        }
    }

    public void validateUserOnboarding(UserOnboardingRequest request) {
        if (Channel.ETC.equals(request.channel())) {
            if (request.channelEtc() == null || request.channelEtc().isBlank()) {
                throw new BusinessException(ErrorCode.ETC_DETAIL_REQUIRED);
            }
        }

        if (Purpose.ETC.equals(request.purpose())) {
            if (request.purposeEtc() == null || request.purposeEtc().isBlank()) {
                throw new BusinessException(ErrorCode.ETC_DETAIL_REQUIRED);
            }
        }
    }
}
