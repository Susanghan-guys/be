package com.susanghan_guys.server.personalwork.application.port;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;

public interface WorkSummaryPort {
    WorkSummaryResponse createWorkSummary(OpenAiRequest request);
}
