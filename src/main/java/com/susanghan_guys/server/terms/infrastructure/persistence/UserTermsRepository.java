package com.susanghan_guys.server.terms.infrastructure.persistence;

import com.susanghan_guys.server.terms.domain.UserTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsRepository extends JpaRepository<UserTerms, Long> {
}
