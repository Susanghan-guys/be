package com.susanghan_guys.server.file.infrastructure.mapper;

import com.susanghan_guys.server.file.domain.PdfFile;
import com.susanghan_guys.server.work.domain.type.SourceType;

public class PdfFileMapper {

    public static PdfFile toEntity(
            String fileUrl,
            SourceType sourceType,
            Long sourceId
    ) {
        return PdfFile.builder()
                .fileUrl(fileUrl)
                .sourceType(sourceType)
                .sourceId(sourceId)
                .build();
    }
}
