package com.susanghan_guys.server.feedback.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FeedbackErrorCode implements BaseCode {

    FEEDBACK_ALREADY_EXIST(HttpStatus.CONFLICT, 409, "해당 리포트에 대한 피드백이 이미 제출되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
