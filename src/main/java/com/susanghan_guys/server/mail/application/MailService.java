package com.susanghan_guys.server.mail.application;

import com.susanghan_guys.server.mail.dto.request.MailRequest;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.WorkMember;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final WorkRepository workRepository;

    @Value("${mail.user.redirect-uri}")
    private String userRedirectUri;

    @Value("${mail.work-member.redirect-uri}")
    private String workMemberRedirectUri;

    public void sendMail() {
        try {
            String template = Files.readString(Path.of("src/main/resources/templates/template.html"));
            List<Work> works = workRepository.findAllByReportStatus(ReportStatus.COMPLETED);

            for (Work work : works) {
                if (work.getCode() == null) {
                    work.updateCode(generateCode());
                }
                sendWorkMembers(work, template);
            }
        } catch (IOException e) {
            log.error("🚨 mail template 로딩 중, 오류 발생: {}", e.getMessage());
            throw new RuntimeException(e); // TODO: errorCode 수정
        }
    }

    private void sendWorkMembers(Work work, String template) {
        personalizeMail(new MailRequest(
                work.getUser().getEmail(),
                work.getUser().getName(),
                work.getTitle(),
                userRedirectUri,
                work.getCode(),
                "[%s] 수상 리포트 완성 안내".formatted(work.getTitle())
        ), template);

        for (WorkMember workMember : work.getWorkMembers()) {
            personalizeMail(new MailRequest(
                    workMember.getTeamMember().getEmail(),
                    workMember.getTeamMember().getName(),
                    work.getTitle(),
                    generateWorkMemberLink(work),
                    work.getCode(),
                    "[%s] 수상 리포트 완성 안내".formatted(work.getTitle())
            ), template);
        }
    }

    private void personalizeMail(MailRequest request, String template) {
        String html = template
                .replace("{{NAME}}", request.name())
                .replace("{{REPORT_TITLE}}", request.title())
                .replace("{{REPORT_LINK}}", request.link())
                .replace("{{REPORT_CODE}}", request.code());

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(request.mail());
            helper.setSubject(request.subject());
            helper.setText(html, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("🚨 메일 전송 중, 오류 발생: {}", e.getMessage());
            throw new RuntimeException(e); // TODO: errorCode 수정
        }
    }

    private String generateWorkMemberLink(Work work) {
        return workMemberRedirectUri + work.getId() + "/verify-code";
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
