package com.susanghan_guys.server.user.validator;

import com.susanghan_guys.server.user.domain.type.Channel;
import com.susanghan_guys.server.user.domain.type.Purpose;
import com.susanghan_guys.server.user.dto.request.UserOnboardingRequest;
import com.susanghan_guys.server.user.dto.request.UserTermsRequest;
import com.susanghan_guys.server.user.exception.UserException;
import com.susanghan_guys.server.user.exception.code.UserErrorCode;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateUserAgreement(UserTermsRequest request) {
        if (!request.isServiceAgreement() || !request.isUserInfoAgreement()) {
            throw new UserException(UserErrorCode.REQUIRED_TERMS_NOT_AGREED);
        }
    }

    public void validateUserOnboarding(UserOnboardingRequest request) {
        if (Channel.ETC.equals(request.channel())) {
            if (request.channelEtc() == null || request.channelEtc().isBlank()) {
                throw new UserException(UserErrorCode.ETC_DETAIL_REQUIRED);
            }
        }

        if (Purpose.ETC.equals(request.purpose())) {
            if (request.purposeEtc() == null || request.purposeEtc().isBlank()) {
                throw new UserException(UserErrorCode.ETC_DETAIL_REQUIRED);
            }
        }
    }
}
