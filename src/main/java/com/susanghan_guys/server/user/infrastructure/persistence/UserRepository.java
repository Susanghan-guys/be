package com.susanghan_guys.server.user.infrastructure.persistence;

import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderIdAndSocialLogin(String providerId, SocialLogin socialLogin);
}
