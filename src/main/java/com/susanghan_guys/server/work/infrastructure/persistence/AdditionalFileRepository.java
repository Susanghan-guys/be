package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.AdditionalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalFileRepository extends JpaRepository<AdditionalFile, Long> {
}