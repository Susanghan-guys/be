package com.susanghan_guys.server.personalwork.application.port;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.*;

public interface OpenAiPort {
    WorkSummaryResponse createWorkSummary(OpenAiRequest request);
    YccWorkEvaluationResponse createYccWorkEvaluation(OpenAiRequest request);
    DetailEvaluationResponse createYccDetailEvaluation(OpenAiRequest request, EvaluationType type);
    DcaWorkEvaluationResponse createDcaWorkEvaluation(DcaOpenAiRequest request);
    DetailEvaluationResponse createDcaDetailEvaluation(DcaOpenAiRequest request, EvaluationType type);
    DcaBriefEvaluationResponse createDcaBriefEvaluation(OpenAiRequest request);

}
