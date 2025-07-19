package com.susanghan_guys.server.user.exception;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.exception.code.UserAuthErrorCode;
import lombok.Getter;

@Getter
public class UserAuthException extends BusinessException {
    public UserAuthException(UserAuthErrorCode errorCode) {
        super(errorCode);
    }
}
