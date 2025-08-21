package com.susanghan_guys.server.personalwork.application.factory;

import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.personalwork.domain.BrandBrief;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.BrandBriefMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.BrandBriefRepository;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OpenAiFactory {

    private final PdfFilePort pdfFilePort;
    private final WorkRepository workRepository;
    private final BrandBriefRepository brandBriefRepository;
    private final BrandBriefMapper brandBriefMapper;

    public OpenAiRequest buildYccOpenAiRequest(Long workId) {
        List<String> imageUrls = pdfFilePort.convertYccPdfToImage(workId).stream()
                .map(PdfImage::getImageUrl)
                .filter(Objects::nonNull)
                .toList();

        if (imageUrls.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_IMAGE_NOT_FOUND);
        }

        return new OpenAiRequest(imageUrls);
    }

    // DCA - 이미지 + 브랜드 브리프
    public DcaOpenAiRequest buildDcaEvaluationRequest(Long workId) {
        List<String> imageUrls = collectDcaImageUrls(workId);

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.WORK_IMAGE_NOT_FOUND));

        BrandBrief brief = brandBriefRepository.findByBrand(work.getBrand()).orElse(null);
        DcaOpenAiRequest.BrandBriefPayload payload = brandBriefMapper.toPayload(brief);

        return new DcaOpenAiRequest(imageUrls, payload);
    }

    private List<String> collectDcaImageUrls(Long workId) {
        List<String> imageUrls = new ArrayList<>(workRepository.findWorkContentByWorkId(workId));

        List<PdfImage> pdfImages = pdfFilePort.convertDcaPdfToImage(workId);
        imageUrls.addAll(
                pdfImages.stream()
                        .map(PdfImage::getImageUrl)
                        .filter(Objects::nonNull)
                        .toList()
        );

        if (imageUrls.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_IMAGE_NOT_FOUND);
        }

        return imageUrls;
    }
}
