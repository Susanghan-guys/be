package com.susanghan_guys.server.contest.infrastructure.persistence;

import com.susanghan_guys.server.contest.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContestRepository extends JpaRepository<Contest, Long> {

    Optional<Contest> findByTitleIgnoreCase(String title);
}