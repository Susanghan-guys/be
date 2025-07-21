package com.susanghan_guys.server.work.exception;


import com.susanghan_guys.server.global.common.code.BaseCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.exception.code.UserErrorCode;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class WorkException extends BusinessException {
    public WorkException(WorkErrorCode errorCode) {
        super(errorCode);
    }
}