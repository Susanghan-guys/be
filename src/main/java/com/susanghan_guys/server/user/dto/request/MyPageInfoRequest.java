package com.susanghan_guys.server.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "사용자 정보 수정")
public record MyPageInfoRequest(
        @Schema(description = "사용자 이름", example = "주정빈")
        String name
) {
}
