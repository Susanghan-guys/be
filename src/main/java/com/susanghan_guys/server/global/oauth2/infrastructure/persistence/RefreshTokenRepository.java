package com.susanghan_guys.server.global.oauth2.infrastructure.persistence;

import com.susanghan_guys.server.global.oauth2.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
