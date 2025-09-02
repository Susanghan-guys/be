package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "내 리포트 정보 조회 API")
public record ReportInfoResponse(
        @Schema(description = "출품작 이름", example = "너에게서 나를 보다")
        String workName,

        @Schema(description = "공모전 이름", example = "YCC")
        String contestName,

        @Schema(description = "브랜드", example = "롯데리아")
        Brand brand,

        @Schema(description = "공모전 팀원", example = "[\"김철수\", \"주정빈\", \"강수진\"]")
        List<String> workMembers,

        boolean hasFeedback
) {
    public static ReportInfoResponse from(Work work, boolean hasFeedback) {
        return ReportInfoResponse.builder()
                .workName(work.getTitle())
                .contestName(work.getContest().getTitle())
                .brand(work.getBrand())
                .workMembers(
                        work.getWorkMembers().stream()
                                .map(workMember -> workMember.getTeamMember().getName())
                                .toList()
                )
                .hasFeedback(hasFeedback)
                .build();
    }
}
