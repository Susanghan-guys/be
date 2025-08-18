package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.YccDetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YccWorkEvaluationService {

    private final CurrentUserProvider currentUserProvider;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;

    public YccWorkEvaluationResponse createYccWorkEvaluation(Long workId) {
        currentUserProvider.getCurrentUser();
        OpenAiRequest request = openAiFactory.buildYccOpenAiRequest(workId);

        // TODO: DB 저장 코드 구현

        return openAiPort.createWorkEvaluation(request);
    }

    public YccDetailEvaluationResponse createYccDetailEvaluation(Long workId, EvaluationType type) {
        currentUserProvider.getCurrentUser();
        OpenAiRequest request = openAiFactory.buildYccOpenAiRequest(workId);

        // TODO: DB 저장 코드 구현

        return openAiPort.createDetailEvaluation(request, type);
    }
}
