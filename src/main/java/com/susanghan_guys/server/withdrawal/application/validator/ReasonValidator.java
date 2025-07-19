package com.susanghan_guys.server.withdrawal.application.validator;

import com.susanghan_guys.server.user.dto.request.UserWithdrawalRequest;
import com.susanghan_guys.server.withdrawal.domain.type.ReasonType;
import com.susanghan_guys.server.withdrawal.exception.ReasonException;
import com.susanghan_guys.server.withdrawal.exception.code.ReasonErrorCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReasonValidator {

    public void validateWithdrawalReason(UserWithdrawalRequest request) {
        List<ReasonType> reasons = request.withdrawalReasons();
        if (reasons == null || reasons.isEmpty()) {
            throw new ReasonException(ReasonErrorCode.WITHDRAWAL_REASON_REQUIRED);
        }

        if (reasons.contains(ReasonType.ETC)) {
            if (request.etc() == null || request.etc().isBlank()) {
                throw new ReasonException(ReasonErrorCode.ETC_DETAIL_REQUIRED);
            }
        }
    }
}
