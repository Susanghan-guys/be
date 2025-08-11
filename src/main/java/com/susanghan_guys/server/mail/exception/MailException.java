package com.susanghan_guys.server.mail.exception;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.mail.exception.code.MailErrorCode;
import lombok.Getter;

@Getter
public class MailException extends BusinessException {
    public MailException(MailErrorCode errorCode) {
        super(errorCode);
    }
}
