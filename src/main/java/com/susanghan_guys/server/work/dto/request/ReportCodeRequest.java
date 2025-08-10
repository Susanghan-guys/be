package com.susanghan_guys.server.work.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "리포트 코드 인증 API")
public record ReportCodeRequest(
        @NotBlank
        @Schema(description = "발급된 리포트 코드", example = "7E9IU8")
        String code
) {
}
