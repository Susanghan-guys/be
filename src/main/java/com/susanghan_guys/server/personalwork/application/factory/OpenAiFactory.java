package com.susanghan_guys.server.personalwork.application.factory;

import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
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

    public OpenAiRequest buildDcaOpenAiRequest(Long workId) {
        // FIXME: DIP 위반 -> 추후 수정 필수.
        List<String> imageUrls = new ArrayList<>(workRepository.findWorkContentByWorkId(workId));

        List<PdfImage> pdfImages = pdfFilePort.convertDcaPdfToImage(workId);

        // 추가 파일(기획안)이 존재할 경우, 브리프 보드 + 추가 파일(기획안) 함께 전송
        imageUrls.addAll(
                pdfImages.stream()
                        .map(PdfImage::getImageUrl)
                        .filter(Objects::nonNull)
                        .toList()
        );

        if (imageUrls.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_IMAGE_NOT_FOUND);
        }

        return new OpenAiRequest(imageUrls);
    }

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
}
