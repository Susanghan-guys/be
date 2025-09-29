package com.susanghan_guys.server.oauth2.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class RedirectUriFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/oauth2/authorization");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String redirectUri = request.getParameter("redirect");

        if (redirectUri != null && !redirectUri.isBlank()) {
            Cookie cookie = new Cookie("redirect", URLEncoder.encode(redirectUri, StandardCharsets.UTF_8));

            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
        }
        filterChain.doFilter(request, response);
    }
}
