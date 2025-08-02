package com.susanghan_guys.server.file.infrastructure.persistence;

import com.susanghan_guys.server.file.domain.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
    Optional<PdfFile> findBySourceId(Long workId);

    @Query("""
        SELECT pf
        FROM PdfFile pf
        JOIN AdditionalFile af ON af.id = pf.sourceId
        WHERE af.work.id = :workId
        AND af.type = 'PLAN'
        AND pf.sourceType = 'ADDITIONAL_FILE'
    """)
    Optional<PdfFile> findByWorkIdFromAdditionalFile(Long workId);
}
