package com.susanghan_guys.server.user.dto.request;

import lombok.Builder;

@Builder
public record MyPageInfoRequest(
        String name
) {
}
