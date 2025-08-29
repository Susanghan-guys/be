package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "리포트 공유 API")
public record ReportSharingResponse(
        @Schema(description = "작품 ID", defaultValue = "1")
        Long workId,

        @Schema(description = "리포트 링크", defaultValue = "https://www.soosanghan.site/v1/reports/22")
        String link,

        @Schema(description = "리포트 코드", defaultValue = "64E8E8")
        String code
) {
    public static ReportSharingResponse from(Work work) {
        return ReportSharingResponse.builder()
                .workId(work.getId())
                .link("https://www.soosanghan.site/v1/reports/" + work.getId())
                .code(work.getCode())
                .build();
    }
}
