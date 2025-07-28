package com.susanghan_guys.server.personalwork.dto.response;

import lombok.Builder;

@Builder
public record WorkSummaryResponse(
        String target,
        String insight,
        String solution
) {
}
