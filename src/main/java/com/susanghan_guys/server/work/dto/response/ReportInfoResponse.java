package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record ReportInfoResponse(
        @Schema(description = "공모전 이름", example = "YCC")
        String contestName,

        @Schema(description = "브랜드", example = "롯데리아")
        Brand brand,

        @Schema(description = "공모전 팀원", example = "[\"김철수\", \"주정빈\", \"강수진\"]")
        List<String> workMembers
) {
    public static ReportInfoResponse from(Work work) {
        return ReportInfoResponse.builder()
                .contestName(work.getContest().getTitle())
                .brand(work.getBrand())
                .workMembers(
                        work.getWorkMembers().stream()
                                .map(workMember -> workMember.getTeamMember().getName())
                                .toList()
                )
                .build();
    }
}
