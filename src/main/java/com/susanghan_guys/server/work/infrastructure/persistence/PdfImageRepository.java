package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.PdfImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PdfImageRepository extends JpaRepository<PdfImage, Long> {
    Optional<PdfImage> findPdfImageByWorkId(Long workId);
}
