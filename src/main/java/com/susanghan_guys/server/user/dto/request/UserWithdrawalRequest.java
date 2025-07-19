package com.susanghan_guys.server.user.dto.request;

import com.susanghan_guys.server.user.domain.type.WithdrawalReason;
import lombok.Builder;

@Builder
public record UserWithdrawalRequest(
        WithdrawalReason withdrawalReason,
        String etc
) {
}
