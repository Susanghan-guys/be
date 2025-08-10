package com.susanghan_guys.server.mail.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MailSuccessCode implements BaseCode {

    MAIL_SEND_SUCCESS(HttpStatus.OK, 200, "Mail send successful"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
