package com.susanghan_guys.server.withdrawal.infrastructure.persistence;

import com.susanghan_guys.server.withdrawal.domain.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<Reason, Long> {
}
