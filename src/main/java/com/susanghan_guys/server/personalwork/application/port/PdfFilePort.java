package com.susanghan_guys.server.personalwork.application.port;

import com.susanghan_guys.server.file.domain.PdfImage;

import java.util.List;

public interface PdfFilePort {
    List<PdfImage> convertDcaPdfToImage(Long workId);
    List<PdfImage> convertYccPdfToImage(Long workId);
}
