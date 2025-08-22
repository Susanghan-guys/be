package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByWorkId(Long workId);
}
