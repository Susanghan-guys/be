package com.susanghan_guys.server.terms.application;

import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.terms.dto.request.TermsRequest;
import com.susanghan_guys.server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final CurrentUserProvider currentUserProvider;

    @Transactional
    public void saveUserAgreement(TermsRequest request) {
        User user = currentUserProvider.getCurrentUser();

        if (!request.isServiceAgreement() || !request.isUserInfoAgreement()) {
            throw new BusinessException(ErrorCode.REQUIRED_TERMS_NOT_AGREED);
        }
        user.updateUserAgreement(true, true, request.isMarketingAgreement());
    }
}
