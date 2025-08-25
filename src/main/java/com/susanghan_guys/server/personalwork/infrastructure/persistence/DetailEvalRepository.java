package com.susanghan_guys.server.personalwork.infrastructure.persistence;

import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailEvalRepository extends JpaRepository<DetailEval, Long> {
    @Query("""
        SELECT d
        FROM DetailEval d
        JOIN d.evaluation e
        JOIN e.work w
        WHERE w.id = :workId
        AND e.type = :type
    """)
    List<DetailEval> findByWorkIdAndEvaluationType(@Param("workId") Long workId, @Param("type") EvaluationType type);
}
