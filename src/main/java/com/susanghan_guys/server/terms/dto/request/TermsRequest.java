package com.susanghan_guys.server.terms.dto.request;

public record TermsRequest(
        boolean isServiceAgreement,
        boolean isUserInfoAgreement,
        boolean isMarketingAgreement
) {
}
