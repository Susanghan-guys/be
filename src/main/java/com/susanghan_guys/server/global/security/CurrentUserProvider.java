package com.susanghan_guys.server.global.security;

import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.user.exception.UserException;
import com.susanghan_guys.server.user.exception.code.UserErrorCode;
import com.susanghan_guys.server.user.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserProvider {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Long userId = SecurityUtils.getCurrentUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }
}
