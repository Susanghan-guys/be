package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.BriefAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BriefAnalysisRepository extends JpaRepository<BriefAnalysis, Long> {
    Optional<BriefAnalysis> findByWorkId(Long workId);
}