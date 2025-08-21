package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    Optional<Summary> findByWorkId(Long workId);
}
