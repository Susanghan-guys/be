package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiClient;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalWorkService {

    private final OpenAiClient openAiClient;

    public WorkSummaryResponse createWorkSummary(OpenAiRequest request) {
        return openAiClient.createWorkSummary(request);
    }
}
