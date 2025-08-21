package com.susanghan_guys.server.personalwork.infrastructure.mapper;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.personalwork.domain.BrandBrief;
import org.springframework.stereotype.Component;

@Component
public class BrandBriefMapper {
    public DcaOpenAiRequest.BrandBriefPayload toPayload(BrandBrief brief) {
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