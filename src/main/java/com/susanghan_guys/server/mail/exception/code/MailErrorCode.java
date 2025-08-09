package com.susanghan_guys.server.mail.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MailErrorCode implements BaseCode {

    MAIL_TEMPLATE_LOADING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "메일 템블릿 로딩 중, 오류가 발생했습니다."),
    MAIL_SENDING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "메일 전송 중, 오류가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
