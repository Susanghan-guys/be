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
    public static MailRequest of(String mail, String name, String title, String link, String code, String subject) {
        return MailRequest.builder()
                .mail(mail)
                .name(name)
                .title(title)
                .link(link)
                .code(code)
                .subject(subject)
                .build();
    }
}
