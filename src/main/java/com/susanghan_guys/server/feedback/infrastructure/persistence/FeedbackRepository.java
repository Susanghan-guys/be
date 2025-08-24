package com.susanghan_guys.server.feedback.infrastructure.persistence;

import com.susanghan_guys.server.feedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    boolean existsByWorkId(Long workId);
}
