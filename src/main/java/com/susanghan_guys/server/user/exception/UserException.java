package com.susanghan_guys.server.user.exception;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.exception.code.UserErrorCode;
import lombok.Getter;

@Getter
public class UserException extends BusinessException {
    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }
}
