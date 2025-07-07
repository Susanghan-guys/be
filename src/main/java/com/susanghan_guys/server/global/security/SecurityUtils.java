package com.susanghan_guys.server.global.security;

import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.domain.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
            !authentication.isAuthenticated() ||
            authentication instanceof AnonymousAuthenticationToken
        ) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
        return ((CustomUserDetails) authentication.getPrincipal()).getUser();
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static String getCurrentUserEmail() {
        return getCurrentUser().getEmail();
    }
}
