package com.susanghan_guys.server.mail.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.mail.application.MailService;
import com.susanghan_guys.server.mail.presentation.swagger.MailSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.mail.presentation.response.MailSuccessCode.MAIL_SEND_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mail")
public class MailController implements MailSwagger {

    private final MailService mailService;

    @Override
    @PostMapping
    public CommonResponse<String> sendMail() {
        mailService.sendMail();
        return CommonResponse.success(MAIL_SEND_SUCCESS, "OK");
    }
}
