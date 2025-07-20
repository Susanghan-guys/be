package com.susanghan_guys.server.work.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "팀원 정보 DTO")
public record TeamMemberDto(

        @Schema(description = "이름", example = "김철수")
        String name,

        @Schema(description = "이메일", example = "kim@example.com")
        String email

) {}