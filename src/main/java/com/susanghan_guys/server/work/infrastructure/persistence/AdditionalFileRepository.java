package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.AdditionalFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdditionalFileRepository extends JpaRepository<AdditionalFile, Long> {
    Optional<AdditionalFile> findAdditionalFileByWorkId(Long workId);
}