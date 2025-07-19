package com.susanghan_guys.server.global.exception;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final BaseCode errorCode;

    public BusinessException(BaseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
