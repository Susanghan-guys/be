package com.susanghan_guys.server.global.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements BaseCode {

    // health_check
    HEALTH_CHECK_SUCCESS(HttpStatus.OK, 200, "Health Check Success"),

    // work
    WORK_DCA_SUBMIT_SUCCESS(HttpStatus.OK, 200, "DCA Work Submission Success"),
    WORK_YCC_SUBMIT_SUCCESS(HttpStatus.OK, 200, "YCC Work Submission Success"),
    MY_REPORTS_RETRIEVED_SUCCESS(HttpStatus.OK, 200, "My Reports Retrieved Success"),
    WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Work Summary Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
