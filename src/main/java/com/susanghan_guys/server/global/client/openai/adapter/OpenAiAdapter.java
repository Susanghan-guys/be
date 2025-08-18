package com.susanghan_guys.server.global.client.openai.adapter;

import com.susanghan_guys.server.global.client.openai.OpenAiClient;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.client.openai.prompt.WorkEvaluationPrompt;
import com.susanghan_guys.server.global.client.openai.prompt.WorkSummaryPrompt;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenAiAdapter implements OpenAiPort {

    private final OpenAiClient openAiClient;

    @Override
    public WorkSummaryResponse createWorkSummary(OpenAiRequest request) {
        return openAiClient.callWithStructuredOutput(
                request,
                WorkSummaryPrompt.buildWorkSummaryPrompt(),
                WorkSummaryResponse.class
        );
    }

    @Override
    public YccWorkEvaluationResponse createWorkEvaluation(OpenAiRequest request) {
        return openAiClient.callWithStructuredOutput(
                request,
                WorkEvaluationPrompt.buildWorkEvaluationPrompt(),
                YccWorkEvaluationResponse.class
        );
    }
}
