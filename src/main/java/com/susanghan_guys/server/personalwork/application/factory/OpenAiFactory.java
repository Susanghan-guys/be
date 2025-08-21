package com.susanghan_guys.server.personalwork.application.factory;

import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OpenAiFactory {

    private final PdfFilePort pdfFilePort;

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
