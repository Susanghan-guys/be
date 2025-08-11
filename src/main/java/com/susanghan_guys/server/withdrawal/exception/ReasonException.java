package com.susanghan_guys.server.withdrawal.exception;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.withdrawal.exception.code.ReasonErrorCode;
import lombok.Getter;

@Getter
public class ReasonException extends BusinessException {
    public ReasonException(ReasonErrorCode errorCode) {
        super(errorCode);
    }
}
