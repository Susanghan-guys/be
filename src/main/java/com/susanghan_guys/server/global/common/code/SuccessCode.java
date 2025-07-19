package com.susanghan_guys.server.global.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements BaseCode {

    // health_check
    HEALTH_CHECK_SUCCESS(HttpStatus.OK, 200, "Health Check Success"),

    // token
    REFRESH_TOKEN_UPDATE_SUCCESS(HttpStatus.OK, 200, "Refresh Token Update Success"),
    EXCHANGE_TOKEN_SUCCESS(HttpStatus.OK, 200, "Exchange Token Success"),

    // user
    USER_LOGOUT_SUCCESS(HttpStatus.OK, 200, "User Logout Success"),
    USER_AGREEMENT_SUCCESS(HttpStatus.OK, 200, "User Agreement Success"),
    USER_ONBOARDING_SUCCESS(HttpStatus.OK, 200, "User Onboarding Success"),

    USER_INFO_SUCCESS(HttpStatus.OK, 200, "User Info Success"),
    USER_INFO_UPDATE_SUCCESS(HttpStatus.OK, 200, "User Info Update Success"),
    USER_WITHDRAWAL_SUCCESS(HttpStatus.OK, 200, "User Withdrawal Success"),

    // work
    WORK_DCA_SUBMIT_SUCCESS(HttpStatus.OK, 200, "DCA Work Submission Success"),
    WORK_YCC_SUBMIT_SUCCESS(HttpStatus.OK, 200, "YCC Work Submission Success")
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
