package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
    @Query("""
        SELECT pf
        FROM PdfFile pf
        LEFT JOIN AdditionalFile af
            ON af.id = pf.sourceId AND pf.sourceType = 'ADDITIONAL_FILE'
        LEFT JOIN Work w
            ON (pf.sourceType = 'WORK' AND pf.sourceId = w.id)
            OR (pf.sourceType = 'ADDITIONAL_FILE' AND af.work.id = w.id)
        WHERE w.id = :workId
    """)
    Optional<PdfFile> findByWorkIdOrAdditionalFile(Long workId);
}
