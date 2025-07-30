package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.personalwork.application.port.WorkSummaryPort;
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

    public WorkSummaryResponse createWorkSummary(Long workId) {
        List<String> imageUrls = workRepository.findByWorkByWorkId(workId);

        if (imageUrls.isEmpty()) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        OpenAiRequest request = new OpenAiRequest(imageUrls);
        return workSummaryPort.createWorkSummary(request);
    }
}
