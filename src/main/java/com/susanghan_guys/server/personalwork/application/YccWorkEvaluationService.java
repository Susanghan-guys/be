package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.personalwork.dto.response.YccWorkEvaluationResponse;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class YccWorkEvaluationService {

    private final CurrentUserProvider currentUserProvider;
    private final WorkRepository workRepository;
    private final OpenAiPort openAiPort;
    private final PdfFilePort pdfFilePort;

    public YccWorkEvaluationResponse createYccWorkEvaluation(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<PdfImage> pdfImages = pdfFilePort.convertYccPdfToImage(workId);

        List<String> imageUrls = pdfImages.stream()
                .map(PdfImage::getImageUrl)
                .filter(Objects::nonNull)
                .toList();

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        // TODO: DB 저장 코드 구현

        return openAiPort.createWorkEvaluation(request);
    }
}
