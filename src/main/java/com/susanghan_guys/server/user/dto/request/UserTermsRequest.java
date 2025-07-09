package com.susanghan_guys.server.user.dto.request;

public record UserTermsRequest(
        Boolean isServiceAgreement,
        Boolean isUserInfoAgreement,
        Boolean isMarketingAgreement
) {
}
