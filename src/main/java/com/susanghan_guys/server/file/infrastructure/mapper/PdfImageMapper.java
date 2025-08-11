package com.susanghan_guys.server.file.infrastructure.mapper;

import com.susanghan_guys.server.file.domain.PdfFile;
import com.susanghan_guys.server.file.domain.PdfImage;

public class PdfImageMapper {

    public static PdfImage toEntity(String imageUrl, PdfFile pdfFile) {
        return PdfImage.builder()
                .imageUrl(imageUrl)
                .pdfFile(pdfFile)
                .build();
    }
}
