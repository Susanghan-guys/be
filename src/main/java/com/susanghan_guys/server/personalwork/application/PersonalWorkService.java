package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.WorkSummaryPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.work.domain.PdfFile;
import com.susanghan_guys.server.work.domain.PdfImage;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonalWorkService {

    private final WorkSummaryPort workSummaryPort;
    private final PersonalWorkValidator personalWorkValidator;
    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;

    public WorkSummaryResponse createDcaWorkSummary(Long workId) {
        PdfFile pdfFile = pdfFileRepository.findBySourceId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<PdfImage> pdfImages = pdfImageRepository.findAllByPdfFile(pdfFile);

        List<String> imageUrls = pdfImages.stream()
                .map(PdfImage::getImageUrl)
                .filter(Objects::nonNull)
                .toList();

        personalWorkValidator.validatePersonalWork(imageUrls);

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        return workSummaryPort.createDcaWorkSummary(request);
    }
}
