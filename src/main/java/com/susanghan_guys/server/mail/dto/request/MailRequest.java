package com.susanghan_guys.server.mail.dto.request;

import lombok.Builder;

@Builder
public record MailRequest(
        String mail,
        String name,
        String title,
        String link,
        String code,
        String subject
) {
}
