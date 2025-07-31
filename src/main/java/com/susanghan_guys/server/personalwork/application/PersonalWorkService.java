package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.WorkSummaryPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.work.domain.PdfImage;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalWorkService {

    private final WorkRepository workRepository;
    private final WorkSummaryPort workSummaryPort;
    private final PersonalWorkValidator personalWorkValidator;
    private final PdfImageRepository pdfImageRepository;

    public WorkSummaryResponse createDcaWorkSummary(Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        PdfImage pdfImage = pdfImageRepository.findPdfImageByWorkId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<String> imageUrls = workRepository.findByWorkByWorkId(workId);

        List<String> pdfImageUrls = new ArrayList<>();
        if (pdfImage.getImageUrl() != null && pdfImage.getWork().equals(work)) {
            pdfImageUrls.add(pdfImage.getImageUrl());
        }
        imageUrls.addAll(pdfImageUrls);

        personalWorkValidator.validatePersonalWork(imageUrls);

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        return workSummaryPort.createDcaWorkSummary(request);
    }
}
