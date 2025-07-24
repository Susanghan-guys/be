package com.susanghan_guys.server.work.dto;

import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "DCA 공모전 작품 제출 요청 DTO")
public record DcaWorkSubmissionRequest(

        @NotBlank
        @Schema(description = "작품 제목", example = "작품_제목")
        String title,

        @NotBlank
        @Schema(description = "작품 접수 번호", example = "P-004")
        String number,

        @NotBlank
        @Schema(
                description = "카테고리 (VISUAL, FILM, DIGICON, EXP, OUTACT)",
                example = "FILM"
        )
        Category category,

        @NotBlank
        @Schema(
                description = "브랜드(BBABBARO, TAMS, KRUSH, AIRISM, LOTTE_WORLD, " +
                        "LOTTE_GIANTS, LOTTERIA, NEXEN_TIRE, SBI_BANK)",
                example = "LOTTERIA"
        )
        Brand brand,

        @Schema(description = "유튜브 링크(영상일 경우 필수)", example = "https://youtube.com/**")
        String youtubeUrl,

        @Schema(description = "팀원 정보(0~n명)")
        List<TeamMemberResponse> teamMembers

) {}
