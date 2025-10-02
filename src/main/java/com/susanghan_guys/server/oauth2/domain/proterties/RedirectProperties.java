package com.susanghan_guys.server.oauth2.domain.proterties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "frontend.oauth2")
public record RedirectProperties(
        List<String> allowedRedirectOrigins
) {
}
