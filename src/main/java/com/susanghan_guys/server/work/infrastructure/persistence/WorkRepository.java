package com.susanghan_guys.server.work.infrastructure.persistence;

import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Optional<Work> findByContestIdAndNumberAndNumberIsNotNull(Long contestId, String number);
    Slice<Work> findByUser(User user, Pageable pageable);
    Slice<Work> findByUserAndContest_Title(User user, String title, Pageable pageable);

    @Query("SELECT w.work FROM Work w WHERE w.id = :workId")
    List<String> findByWorkByWorkId(@Param("workId") Long workId);
}

