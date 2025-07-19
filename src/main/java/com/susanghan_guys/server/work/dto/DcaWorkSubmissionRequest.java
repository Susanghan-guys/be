package com.susanghan_guys.server.work.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "DCA 공모전 작품 제출 요청 DTO")
public record DcaWorkSubmissionRequest(

        @Schema(description = "작품 제목", example = "작품_제목")
        String title,

        @Schema(description = "작품 접수 번호", example = "P-004")
        String number,

        @Schema(
                description = "카테고리 (VISUAL, FILM, DIGICON, EXP, OUTACT)",
                example = "FILM"
        )
        String category,

        @Schema(
                description = "브랜드(BBABBARO, TAMS, KRUSH, AIRISM, LOTTE_WORLD, " +
                        "LOTTE_GIANTS, LOTTERIA, NEXEN_TIRE, SBI_BANK)",
                example = "LOTTERIA"
        )
        String brand,

        @Schema(
                description = "추가자료 유형 (PLAN - 기획서, VIDEO - 영상 링크)",
                example = "VIDEO"
        )
        String filesType,

        @Schema(description = "유튜브 링크(영상일 경우 필수)", example = "https://youtube.com/**")
        String youtubeUrl,

        @Schema(description = "팀원 정보(0~n명)")
        List<TeamMemberDto> members

) {}
