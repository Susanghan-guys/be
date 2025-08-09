package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Optional<Work> findByContestIdAndNumberAndNumberIsNotNull(Long contestId, String number);
    @Query("SELECT w.work FROM Work w WHERE w.id = :workId")
    List<String> findWorkContentByWorkId(@Param("workId") Long workId);
    List<Work> findAllByReportStatus(ReportStatus reportStatus);
    @Query("""
        SELECT DISTINCT w
        FROM Work w
        LEFT JOIN WorkVisibility wv on wv.work = w AND wv.visible = true AND wv.user.id = :userId
        WHERE (w.user.id = :userId OR wv.id IS NOT NULL)
        AND (:name IS NULL OR :name = '' OR w.contest.title = :name)
        ORDER BY w.createdAt DESC
    """)
    Slice<Work> findAccessibleWorks(Long userId, String name, Pageable pageable);
    @Query("""
        SELECT wv.work.id
        FROM WorkVisibility wv
        WHERE wv.visible = true
        AND wv.user.id = :userId
        AND wv.work.id IN :workIds
        AND wv.work.user.id <> :userId
        GROUP BY wv.work.id
    """)
    Set<Long> findDeletableWorks(Collection<Long> workIds, Long userId);
}

