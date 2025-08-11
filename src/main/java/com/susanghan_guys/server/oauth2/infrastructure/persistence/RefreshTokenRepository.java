package com.susanghan_guys.server.oauth2.infrastructure.persistence;

import com.susanghan_guys.server.oauth2.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
}
