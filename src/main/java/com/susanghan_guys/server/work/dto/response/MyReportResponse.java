package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "내 리포트 조회 API")
public record MyReportResponse(
        @Schema(description = "작품 고유 ID", example = "201")
        Long workId,

        @Schema(description = "공모전 이름", example = "YCC")
        String contestName,

        @Schema(description = "출품작 제목", example = "너에게서 나를 보다")
        String title,

        @Schema(description = "카테고리", example = "Visual")
        Category category,

        @Schema(description = "브랜드", example = "롯데리아")
        Brand brand,

        @Schema(description = "리포트 제작 진행 상황", example = "COMPLETED")
        ReportStatus reportStatus,

        @Schema(description = "삭제 가능 여부", example = "true")
        boolean isDeletable,

        @Schema(description = "공모전 팀원", example = "[\"김철수\", \"주정빈\", \"강수진\"]")
        List<String> workMembers
) {
    public static MyReportResponse of(Work work, boolean isDeletable) {
        return MyReportResponse.builder()
                .workId(work.getId())
                .contestName(work.getContest().getTitle())
                .title(work.getTitle())
                .category(work.getCategory())
                .brand(work.getBrand())
                .reportStatus(work.getReportStatus())
                .isDeletable(isDeletable)
                .workMembers(
                        work.getWorkMembers().stream()
                                .map(workMember -> workMember.getTeamMember().getName())
                                .toList()
                )
                .build();
    }
}
