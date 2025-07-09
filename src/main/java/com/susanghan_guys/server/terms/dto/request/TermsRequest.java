package com.susanghan_guys.server.terms.dto.request;

public record TermsRequest(
        Boolean isServiceAgreement,
        Boolean isUserInfoAgreement,
        Boolean isMarketingAgreement
) {
}
