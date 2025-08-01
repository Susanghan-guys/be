package com.susanghan_guys.server.work.infrastructure.mapper;

import com.susanghan_guys.server.work.domain.PdfFile;
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
