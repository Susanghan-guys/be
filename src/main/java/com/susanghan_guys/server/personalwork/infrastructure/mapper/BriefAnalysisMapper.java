package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.personalwork.domain.BriefAnalysis;
import com.susanghan_guys.server.personalwork.dto.response.DcaBriefEvaluationResponse;
import com.susanghan_guys.server.work.domain.Work;

public class BriefAnalysisMapper {

    private static final int MAX_LEN = 300;

    public static BriefAnalysis toEntity(Work work, DcaBriefEvaluationResponse resp) {
        return BriefAnalysis.builder()
                .interpretation(resp.interpretation())
                .consistency(safeTrim(resp.consistency(), MAX_LEN))
                .weakness(safeTrim(resp.weakness(), MAX_LEN))
                .work(work)
                .build();
    }

    public static DcaBriefEvaluationResponse toResponse(BriefAnalysis e) {
        return DcaBriefEvaluationResponse.builder()
                .interpretation(e.getInterpretation())
                .consistency(safeTrim(e.getConsistency(), MAX_LEN))
                .weakness(safeTrim(e.getWeakness(), MAX_LEN))
                .build();
    }

    private static String safeTrim(String s, int maxCp) {
        if (s == null) return null;
        int cpCount = s.codePointCount(0, s.length());
        if (cpCount <= maxCp) return s;
        int end = s.offsetByCodePoints(0, maxCp);
        return s.substring(0, end);
    }
}