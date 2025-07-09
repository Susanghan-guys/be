package com.susanghan_guys.server.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "사용자 이용 약관 동의")
public record UserTermsRequest(
        @Schema(
                description = "서비스 이용 약관 동의",
                example = "true"
        )
        Boolean isServiceAgreement,

        @Schema(
                description = "사용자 정보 수집 동의",
                example = "true"
        )
        Boolean isUserInfoAgreement,

        @Schema(
                description = "마케팅 수신 동의",
                example = "false"
        )
        Boolean isMarketingAgreement
) {
}
