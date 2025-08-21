package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
}
