package com.susanghan_guys.server.global.client.openai;

import java.util.List;

public record DcaOpenAiRequest(
        List<String> imageUrls,
        BrandBriefPayload brandBrief // null이면 브리프 미제공
) implements AiRequest {

    public record BrandBriefPayload(
            String brandIntro,
            String marketStatus,
            String brandCommTarget,
            String challenge,
            String cautions
    ) {}
}