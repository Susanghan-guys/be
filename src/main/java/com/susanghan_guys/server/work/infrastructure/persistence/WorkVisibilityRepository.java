package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.WorkVisibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkVisibilityRepository extends JpaRepository<WorkVisibility, Long> {
    Optional<WorkVisibility> findByWorkIdAndUserId(Long workId, Long userId);
}
