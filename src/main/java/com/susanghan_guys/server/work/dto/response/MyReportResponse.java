package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record MyReportResponse(
        String contestName,
        String title,
        Category category,
        Brand brand,
        ReportStatus reportStatus,
        List<String> workMembers
) {
    public static MyReportResponse from(Work work) {
        return MyReportResponse.builder()
                .contestName(work.getContest().getTitle())
                .title(work.getTitle())
                .category(work.getCategory())
                .brand(work.getBrand())
                .reportStatus(work.getReportStatus())
                .workMembers(
                        work.getWorkMembers().stream()
                                .map(workMember -> workMember.getTeamMember().getName())
                                .toList()
                )
                .build();
    }
}
