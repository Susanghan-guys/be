package com.susanghan_guys.server.work.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "리포트 삭제 API (신청자일 경우, 삭제 불가능)")
public record ReportDeletionRequest(
        @Schema(description = "출품작 제목", example = "너에게서 나를 보다")
        String title
) {
}
