package com.susanghan_guys.server.global.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements BaseCode {

    // health_check
    HEALTH_CHECK_SUCCESS(HttpStatus.OK, 200, "Health Check Success"),

    // user
    USER_LOGOUT_SUCCESS(HttpStatus.OK, 200, "User Logout Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
