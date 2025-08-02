package com.susanghan_guys.server.file.infrastructure.saver;

import com.susanghan_guys.server.file.domain.type.FilesType;
import com.susanghan_guys.server.work.domain.AdditionalFile;
import com.susanghan_guys.server.file.domain.PdfFile;
import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.work.domain.type.SourceType;
import com.susanghan_guys.server.file.infrastructure.mapper.PdfFileMapper;
import com.susanghan_guys.server.file.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.file.infrastructure.persistence.PdfImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PdfFileSaver {

    private final PdfFileRepository pdfFileRepository;
    private final PdfImageRepository pdfImageRepository;

    @Transactional
    public void savePdfFile(AdditionalFile additionalFile) {
        if (additionalFile.getType() != FilesType.PLAN) return;

        pdfFileRepository.save(PdfFileMapper.toEntity
                (additionalFile.getValue(), SourceType.ADDITIONAL_FILE, additionalFile.getId())
        );
    }

    @Transactional
    public List<PdfImage> savePdfImage(List<String> imageUrls, PdfFile pdfFile) {
        List<PdfImage> pdfImages = imageUrls.stream()
                .map(url -> new PdfImage(url, pdfFile))
                .toList();

        return pdfImageRepository.saveAll(pdfImages);
    }
}
