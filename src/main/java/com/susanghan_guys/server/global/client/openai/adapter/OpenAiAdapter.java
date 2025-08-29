package com.susanghan_guys.server.global.client.openai.adapter;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiClient;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.client.openai.prompt.DcaBriefEvaluationPrompt;
import com.susanghan_guys.server.global.client.openai.prompt.DcaWorkEvaluationPrompt;
import com.susanghan_guys.server.global.client.openai.prompt.WorkSummaryPrompt;
import com.susanghan_guys.server.global.client.openai.prompt.YccWorkEvaluationPrompt;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.*;
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
    public YccWorkEvaluationResponse createYccWorkEvaluation(OpenAiRequest request) {
        return openAiClient.callWithStructuredOutput(
                request,
                YccWorkEvaluationPrompt.buildYccWorkEvaluationPrompt(),
                YccWorkEvaluationResponse.class
        );
    }

    @Override
    public DetailEvaluationResponse createYccDetailEvaluation(OpenAiRequest request, EvaluationType type) {
        return openAiClient.callWithStructuredOutput(
                request,
                YccWorkEvaluationPrompt.buildYccDetailEvaluationPrompt(type),
                DetailEvaluationResponse.class
        );
    }

    @Override
    public DcaWorkEvaluationResponse createDcaWorkEvaluation(DcaOpenAiRequest request) {
        return openAiClient.callWithStructuredOutput(
                request,
                DcaWorkEvaluationPrompt.buildDcaWorkEvaluationPrompt(request.brandBrief()),
                DcaWorkEvaluationResponse.class
        );
    }

    @Override
    public DetailEvaluationResponse createDcaDetailEvaluation(DcaOpenAiRequest request, EvaluationType type) {
        return openAiClient.callWithStructuredOutput(
                request,
                DcaWorkEvaluationPrompt.buildDcaDetailEvaluationPrompt(request.brandBrief(), type),
                DetailEvaluationResponse.class
        );
    }

    @Override
    public DcaBriefEvaluationResponse createDcaBriefEvaluation(DcaOpenAiRequest request) {
        return openAiClient.callWithStructuredOutput(
                request,
                DcaBriefEvaluationPrompt.buildDcaBriefEvaluationPrompt(request.brandBrief()),
                DcaBriefEvaluationResponse.class
        );
    }
}
