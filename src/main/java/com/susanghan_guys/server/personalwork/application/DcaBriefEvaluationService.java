package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.dto.response.DcaBriefEvaluationResponse;
import com.susanghan_guys.server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DcaBriefEvaluationService {

    private final CurrentUserProvider currentUserProvider;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;
    private final PersonalWorkValidator personalWorkValidator;

    public DcaBriefEvaluationResponse createDcaBriefEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        DcaOpenAiRequest request = openAiFactory.buildDcaOpenAiRequestWithBrief(workId);

        // TODO: DB 저장 코드 구현

        return openAiPort.createDcaBriefEvaluation(request);
    }
}
