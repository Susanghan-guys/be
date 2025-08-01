package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.WorkSummaryPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.work.domain.PdfFile;
import com.susanghan_guys.server.work.domain.PdfImage;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfImageRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonalWorkService {

    private final WorkRepository workRepository;
    private final WorkSummaryPort workSummaryPort;
    private final PersonalWorkValidator personalWorkValidator;
    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;

    public WorkSummaryResponse createDcaWorkSummary(Long workId) {
        workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<String> imageUrls = new ArrayList<>(workRepository.findByWorkByWorkId(workId));

        // 추가 파일이 존재할 경우, 추가 파일 + 기획안 함께 전송
        pdfFileRepository.findBySourceId(workId)
                .ifPresent(pdfFile -> {
                    List<String> pdfImageUrls = pdfImageRepository.findAllByPdfFile(pdfFile).stream()
                            .map(PdfImage::getImageUrl)
                            .filter(Objects::nonNull)
                            .toList();

                    imageUrls.addAll(pdfImageUrls);
                });

        personalWorkValidator.validatePersonalWork(imageUrls);

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        return workSummaryPort.createWorkSummary(request);
    }

    public WorkSummaryResponse createYccWorkSummary(Long workId) {
        workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        PdfFile pdfFile = pdfFileRepository.findBySourceId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<PdfImage> pdfImages = pdfImageRepository.findAllByPdfFile(pdfFile);

        List<String> imageUrls = pdfImages.stream()
                .map(PdfImage::getImageUrl)
                .filter(Objects::nonNull)
                .toList();

        personalWorkValidator.validatePersonalWork(imageUrls);

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        return workSummaryPort.createWorkSummary(request);
    }
}
