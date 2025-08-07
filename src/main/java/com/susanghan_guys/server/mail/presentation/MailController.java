package com.susanghan_guys.server.mail.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.global.common.code.SuccessCode;
import com.susanghan_guys.server.mail.application.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("")
    public CommonResponse<String> sendMail() {
        mailService.sendMail();
        return CommonResponse.success(SuccessCode.HEALTH_CHECK_SUCCESS, "OK");
    }
}
