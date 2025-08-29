package com.susanghan_guys.server.feedback.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FeedbackResponse implements BaseCode {

    FEEDBACK_SUBMIT_SUCCESS(HttpStatus.OK, 200, "Feedback submit success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
