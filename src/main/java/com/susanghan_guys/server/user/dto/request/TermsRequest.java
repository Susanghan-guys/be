package com.susanghan_guys.server.user.dto.request;

public record TermsRequest(
        Boolean isServiceAgreement,
        Boolean isUserInfoAgreement,
        Boolean isMarketingAgreement
) {
}
