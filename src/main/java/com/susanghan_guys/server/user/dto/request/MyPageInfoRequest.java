package com.susanghan_guys.server.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "사용자 정보 수정")
public record MyPageInfoRequest(
        @NotBlank(message = "사용자 이름은 필수입니다")
        @Schema(description = "사용자 이름", example = "주정빈")
        String name
) {
}
