package com.susanghan_guys.server.user.dto.request;

import com.susanghan_guys.server.user.domain.type.WithdrawalReason;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserWithdrawalRequest(
        @NotNull
        WithdrawalReason withdrawalReason,
        String etc
) {
}
