package com.susanghan_guys.server.work.infrastructure.saver;

import com.susanghan_guys.server.work.domain.AdditionalFile;
import com.susanghan_guys.server.work.domain.PdfFile;
import com.susanghan_guys.server.work.domain.PdfImage;
import com.susanghan_guys.server.work.domain.type.SourceType;
import com.susanghan_guys.server.work.infrastructure.mapper.PdfFileMapper;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.utils.StringUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PdfFileSaver {

    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;

    @Transactional
    public void savePdfFile(AdditionalFile additionalFile) {
        if (StringUtils.isBlank(additionalFile.getValue())) {
            return;
        }
        String url = additionalFile.getValue();
        String extension = url.substring(url.lastIndexOf(".") + 1);

        if (!extension.equals("pdf")) {
            return;
        }

        pdfFileRepository.save(PdfFileMapper.toEntity
                (additionalFile.getValue(), SourceType.ADDITIONAL_FILE, additionalFile.getId())
        );
    }

    @Transactional
    public void savePdfToImage(List<String> imageUrls, PdfFile pdfFile) {
        for (String imageUrl : imageUrls) {
            pdfImageRepository.save(new PdfImage(imageUrl, pdfFile));
        }
    }
}
