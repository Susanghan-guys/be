package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.Summary;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.work.domain.Work;

public class SummaryMapper {

    public static Summary toEntity(Work work, WorkSummaryResponse response) {
        return Summary.builder()
                .target(response.target())
                .insight(response.insight())
                .solution(response.solution())
                .work(work)
                .build();
    }

    public static WorkSummaryResponse toResponse(Summary summary) {
        return WorkSummaryResponse.builder()
                .target(summary.getTarget())
                .insight(summary.getInsight())
                .solution(summary.getSolution())
                .build();
    }
}
