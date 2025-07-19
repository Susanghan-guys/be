package com.susanghan_guys.server.user.dto.request;

import com.susanghan_guys.server.withdrawal.domain.type.ReasonType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "사용자 탈퇴")
public record UserWithdrawalRequest(
        @NotNull
        @Schema(
                description = "사용자 탈퇴 이유",
                example = "[\"UNKNOWN\", \"SECURITY\"]"
        )
        List<ReasonType> withdrawalReasons,

        @Schema(
                description = "사용자 탈퇴 이유 - 기타일 경우, 작성",
                example = "어떤 무언가"
        )
        String etc
) {
}
