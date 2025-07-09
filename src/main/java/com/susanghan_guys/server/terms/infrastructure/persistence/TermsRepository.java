package com.susanghan_guys.server.terms.infrastructure.persistence;

import com.susanghan_guys.server.terms.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Long> {
}
