package com.susanghan_guys.server.work.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WorkSuccessCode implements BaseCode {

    WORK_DCA_SUBMIT_SUCCESS(HttpStatus.OK, 200, "DCA Work Submission Success"),
    WORK_YCC_SUBMIT_SUCCESS(HttpStatus.OK, 200, "YCC Work Submission Success"),
    MY_REPORTS_RETRIEVED_SUCCESS(HttpStatus.OK, 200, "My Reports Retrieved Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
