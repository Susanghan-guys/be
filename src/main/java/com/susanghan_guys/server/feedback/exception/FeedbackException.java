package com.susanghan_guys.server.feedback.exception;

import com.susanghan_guys.server.feedback.exception.code.FeedbackErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import lombok.Getter;

@Getter
public class FeedbackException extends BusinessException {
    public FeedbackException(FeedbackErrorCode errorCode) {
        super(errorCode);
    }
}
