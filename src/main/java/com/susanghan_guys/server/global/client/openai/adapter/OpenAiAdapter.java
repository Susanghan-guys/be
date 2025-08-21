package com.susanghan_guys.server.global.client.openai.adapter;

import com.susanghan_guys.server.global.client.openai.OpenAiClient;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.client.openai.prompt.YccWorkEvaluationPrompt;
import com.susanghan_guys.server.global.client.openai.prompt.WorkSummaryPrompt;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.YccDetailEvaluationResponse;
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
                YccWorkEvaluationPrompt.buildYccWorkEvaluationPrompt(),
                YccWorkEvaluationResponse.class
        );
    }

    @Override
    public YccDetailEvaluationResponse createDetailEvaluation(OpenAiRequest request, EvaluationType type) {
        return openAiClient.callWithStructuredOutput(
                request,
                YccWorkEvaluationPrompt.buildYccDetailEvaluationPrompt(type),
                YccDetailEvaluationResponse.class
        );
    }
}
