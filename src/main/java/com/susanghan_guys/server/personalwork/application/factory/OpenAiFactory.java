package com.susanghan_guys.server.personalwork.application.factory;

import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.BrandBrief;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.BrandBriefMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.BrandBriefRepository;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OpenAiFactory {

    private final WorkRepository workRepository;
    private final PdfFilePort pdfFilePort;
    private final BrandBriefRepository brandBriefRepository;
    private final PersonalWorkValidator personalWorkValidator;


    public OpenAiRequest buildYccOpenAiRequest(Long workId) {
        List<String> imageUrls = pdfFilePort.convertYccPdfToImage(workId).stream()
                .map(PdfImage::getImageUrl)
                .filter(Objects::nonNull)
                .toList();

        personalWorkValidator.validatePersonalWork(imageUrls);

        return new OpenAiRequest(imageUrls);
    }

    // DCA - only 이미지(+기획안)
    public OpenAiRequest buildDcaOpenAiRequest(Long workId) {

        List<String> imageUrls = collectDcaImageUrls(workId);

        return new OpenAiRequest(imageUrls);
    }

    // DCA - 이미지 + 브랜드 브리프
    public DcaOpenAiRequest buildDcaOpenAiRequestWithBrief(Long workId) {
        List<String> imageUrls = collectDcaImageUrls(workId);

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        DcaOpenAiRequest.BrandBriefPayload payload = null;
        var brand = work.getBrand();
        if (brand != null) {
            BrandBrief brief = brandBriefRepository.findByBrand(brand).orElse(null);
            payload = BrandBriefMapper.toPayload(brief);
        }

        return new DcaOpenAiRequest(imageUrls, payload);
    }

    private List<String> collectDcaImageUrls(Long workId) {
        // FIXME: DIP 위반 -> 추후 수정 필수.
        List<String> imageUrls = new ArrayList<>(workRepository.findWorkContentByWorkId(workId));

        List<PdfImage> pdfImages = pdfFilePort.convertDcaPdfToImage(workId);
        imageUrls.addAll(
                pdfImages.stream()
                        .map(PdfImage::getImageUrl)
                        .filter(Objects::nonNull)
                        .toList()
        );

        personalWorkValidator.validatePersonalWork(imageUrls);

        return imageUrls;
    }
}
