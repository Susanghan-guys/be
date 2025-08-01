package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
    Optional<PdfFile> findBySourceId(Long sourceId);
}
