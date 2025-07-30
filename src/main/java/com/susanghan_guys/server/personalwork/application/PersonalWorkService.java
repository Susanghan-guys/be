package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.WorkSummaryPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalWorkService {

    private final WorkRepository workRepository;
    private final WorkSummaryPort workSummaryPort;
    private final PersonalWorkValidator personalWorkValidator;

    public WorkSummaryResponse createDcaWorkSummary(Long workId) {
        List<String> imageUrls = workRepository.findByWorkByWorkId(workId);

        personalWorkValidator.validatePersonalWork(imageUrls);

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        return workSummaryPort.createDcaWorkSummary(request);
    }
}
