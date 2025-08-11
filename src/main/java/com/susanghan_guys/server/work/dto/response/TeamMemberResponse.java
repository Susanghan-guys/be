package com.susanghan_guys.server.work.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "팀원 정보 DTO")
public record TeamMemberResponse(

        @NotBlank
        @Schema(description = "이름", example = "김철수")
        String name,

        @NotBlank
        @Email
        @Schema(description = "이메일", example = "kim@example.com")
        String email

) {}