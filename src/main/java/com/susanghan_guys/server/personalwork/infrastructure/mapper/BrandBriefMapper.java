package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.personalwork.domain.BrandBrief;

public final class BrandBriefMapper {
    public static DcaOpenAiRequest.BrandBriefPayload toPayload(BrandBrief brief) {
        if (brief == null) return null;
        return new DcaOpenAiRequest.BrandBriefPayload(
                brief.getBrandIntro(),
                brief.getMarketStatus(),
                brief.getBrandCommTarget(),
                brief.getChallenge(),
                brief.getCautions()
        );
    }
}