package com.susanghan_guys.server.mail.application;

import com.susanghan_guys.server.mail.dto.request.MailRequest;
import com.susanghan_guys.server.mail.exception.MailException;
import com.susanghan_guys.server.mail.exception.code.MailErrorCode;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.WorkMember;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final WorkRepository workRepository;

    @Value("${mail.redirect-uri}")
    private String redirectUri;

    @Transactional
    public void sendMail() {
        try {
            String template = new String(
                    new ClassPathResource("templates/template.html")
                            .getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );
            List<Work> works = workRepository.findAllByReportStatusAndMailSentAtIsNull(ReportStatus.COMPLETED);

            for (Work work : works) {
                if (work.getCode() == null) {
                    work.updateCode(generateCode());
                }
                sendWorkMembers(work, template);
                work.markMailSent();
            }
        } catch (IOException e) {
            log.error("🚨 mail template 로딩 중, 오류 발생: {}", e.getMessage());
            throw new MailException(MailErrorCode.MAIL_TEMPLATE_LOADING_FAILED);
        }
    }

    private void sendWorkMembers(Work work, String template) {
        personalizeMail(new MailRequest(
                work.getUser().getEmail(),
                work.getUser().getName(),
                work.getTitle(),
                generateUserLink(work),
                work.getCode(),
                "[%s] 수상 리포트 완성 안내".formatted(work.getTitle())
        ), template);

        for (WorkMember workMember : work.getWorkMembers()) {
            if (work.getUser().getEmail().equals(workMember.getTeamMember().getEmail())) {
                continue;
            }
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
            throw new MailException(MailErrorCode.MAIL_SENDING_FAILED);
        }
    }

    private String generateUserLink(Work work) {
        return redirectUri + work.getId();
    }

    private String generateWorkMemberLink(Work work) {
        return redirectUri + work.getId() + "/verify-code";
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
