package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.work.domain.WorkVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkVisibilityRepository extends JpaRepository<WorkVisibility, Long> {
    Optional<WorkVisibility> findByWorkIdAndUserId(Long workId, Long userId);
    @Modifying
    @Query("""
        UPDATE WorkVisibility wv
        SET wv.visible = false
        WHERE wv.work.id = :workId and wv.user.id = :userId and wv.visible = true
    """)
    int deletedWorks(Long workId, Long userId);
}
