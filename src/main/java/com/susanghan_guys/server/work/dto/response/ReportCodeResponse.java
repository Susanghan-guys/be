package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "리포트 공유 API")
public record ReportCodeResponse(
        Long workId,
        String link,
        String code
) {
    public static ReportCodeResponse from(Work work) {
        return ReportCodeResponse.builder()
                .workId(work.getId())
                .link("https://www.soosanghan.site/v1/reports/" + work.getId())
                .code(work.getCode())
                .build();
    }
}
