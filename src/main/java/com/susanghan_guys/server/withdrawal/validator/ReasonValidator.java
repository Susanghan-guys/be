package com.susanghan_guys.server.withdrawal.validator;

import com.susanghan_guys.server.user.dto.request.UserWithdrawalRequest;
import com.susanghan_guys.server.user.exception.UserException;
import com.susanghan_guys.server.user.exception.code.UserErrorCode;
import com.susanghan_guys.server.withdrawal.domain.type.ReasonType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReasonValidator {

    public void validateWithdrawalReason(UserWithdrawalRequest request) {
        List<ReasonType> reasons = request.withdrawalReasons();
        if (reasons == null || reasons.isEmpty()) {
            throw new UserException(UserErrorCode.WITHDRAWAL_REASON_REQUIRED);
        }

        if (reasons.contains(ReasonType.ETC)) {
            if (request.etc() == null || request.etc().isBlank()) {
                throw new UserException(UserErrorCode.ETC_DETAIL_REQUIRED);
            }
        }
    }
}
