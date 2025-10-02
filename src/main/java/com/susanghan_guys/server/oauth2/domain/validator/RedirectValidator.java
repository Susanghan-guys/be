package com.susanghan_guys.server.oauth2.domain.validator;

import com.susanghan_guys.server.oauth2.domain.proterties.RedirectProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RedirectValidator {

    private final RedirectProperties redirectProperties;

    public boolean isAuthorized(String redirect) {
        try {
            URI uri = new URI(redirect);

            String origin = uri.getScheme() + "://" + uri.getHost();
            if (uri.getPort() != -1) {
                origin += ":" + uri.getPort();
            }

            List<String> allowedOrigins = redirectProperties.allowedRedirectOrigins();
            return allowedOrigins != null && allowedOrigins.contains(origin);
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
