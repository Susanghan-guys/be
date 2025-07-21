package com.susanghan_guys.server.work.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "YCC(HASD) 공모전 작품 제출 요청")
public record YccWorkSubmissionRequest(

        @NotBlank
        @Schema(description = "작품 제목", example = "기획서_제목")
        String title,

        @NotEmpty
        @Schema(description = "팀원 정보 리스트")
        List<TeamMemberResponse> members

) {}