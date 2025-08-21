package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
