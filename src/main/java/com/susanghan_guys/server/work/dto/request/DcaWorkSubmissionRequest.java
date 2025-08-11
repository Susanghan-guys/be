package com.susanghan_guys.server.work.dto.request;

import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.dto.response.TeamMemberResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

        @NotNull
        @Schema(
                description = "카테고리 (Visual, Film, Digital Contents, Experience, Outdoor Activation)",
                example = "Film"
        )
        Category category,

        @NotNull
        @Schema(
                description = "브랜드(빼빼로, 탐스, 크러시, 에어리즘, 롯데월드, " +
                        "롯데자이언츠, 롯데리아, 넥센타이어, SBI 저축은행 사이다뱅크)",
                example = "빼빼로"
        )
        Brand brand,

        @Schema(description = "유튜브 링크(영상일 경우 필수)", example = "https://youtube.com/**")
        String youtubeUrl,

        @Schema(description = "팀원 정보(0~n명)")
        List<TeamMemberResponse> teamMembers

) {}
