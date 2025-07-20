package com.susanghan_guys.server.user.infrastructure.persistence;

import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.domain.type.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderIdAndSocialLogin(String providerId, SocialLogin socialLogin);
}
