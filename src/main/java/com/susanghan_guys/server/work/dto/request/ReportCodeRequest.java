package com.susanghan_guys.server.work.dto.request;

import lombok.Builder;

@Builder
public record ReportCodeRequest(
        String code
) {
}
