package com.susanghan_guys.server.oauth2.domain.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class RedirectValidator {

    @Value("${frontend.oauth2.allowed-redirect-origin}")
    private String allowedOrigin;

    public boolean isAuthorized(String redirect) {
        try {
            URI uri = new URI(redirect);

            String origin = uri.getScheme() + "://" + uri.getHost();
            if (uri.getPort() != -1) {
                origin += ":" + uri.getPort();
            }

            return allowedOrigin.equalsIgnoreCase(origin);
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
