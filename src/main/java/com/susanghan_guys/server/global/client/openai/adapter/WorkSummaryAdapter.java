package com.susanghan_guys.server.global.client.openai.adapter;

import com.susanghan_guys.server.global.client.openai.OpenAiClient;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.client.openai.prompt.WorkSummaryPrompt;
import com.susanghan_guys.server.personalwork.application.port.WorkSummaryPort;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkSummaryAdapter implements WorkSummaryPort {

    private final OpenAiClient openAiClient;

    @Override
    public WorkSummaryResponse createDcaWorkSummary(OpenAiRequest request) {
        return openAiClient.callWithStructuredOutput(
                request,
                WorkSummaryPrompt.buildWorkSummaryPrompt(),
                WorkSummaryResponse.class
        );
    }
}
