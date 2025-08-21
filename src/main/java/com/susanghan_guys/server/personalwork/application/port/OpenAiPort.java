package com.susanghan_guys.server.personalwork.application.port;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DcaWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccDetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;

public interface OpenAiPort {
    WorkSummaryResponse createWorkSummary(OpenAiRequest request);
    YccWorkEvaluationResponse createWorkEvaluation(OpenAiRequest request);
    YccDetailEvaluationResponse createDetailEvaluation(OpenAiRequest request, EvaluationType type);
    DcaWorkEvaluationResponse createDcaWorkEvaluation(DcaOpenAiRequest request);
}
