package com.susanghan_guys.server.work.exception;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import lombok.Getter;

@Getter
public class WorkException extends BusinessException {
    public WorkException(WorkErrorCode errorCode) {
        super(errorCode);
    }
}