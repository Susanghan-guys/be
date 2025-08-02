package com.susanghan_guys.server.file.infrastructure.persistence;

import com.susanghan_guys.server.file.domain.PdfFile;
import com.susanghan_guys.server.file.domain.PdfImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdfImageRepository extends JpaRepository<PdfImage, Long> {
    List<PdfImage> findAllByPdfFile(PdfFile pdfFile);
}
