package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import lombok.Builder;

@Builder
public record ReportCodeResponse(
        Long workId
) {
    public static ReportCodeResponse from(Work work) {
        return ReportCodeResponse.builder()
                .workId(work.getId())
                .build();
    }
}
