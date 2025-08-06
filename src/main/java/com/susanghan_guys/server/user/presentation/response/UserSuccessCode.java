package com.susanghan_guys.server.user.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseCode {

    USER_AGREEMENT_SUCCESS(HttpStatus.OK, 200, "User Agreement Success"),
    USER_ONBOARDING_SUCCESS(HttpStatus.OK, 200, "User Onboarding Success"),
    USER_INFO_SUCCESS(HttpStatus.OK, 200, "User Info Success"),
    USER_INFO_UPDATE_SUCCESS(HttpStatus.OK, 200, "User Info Update Success"),
    USER_WITHDRAWAL_SUCCESS(HttpStatus.OK, 200, "User Withdrawal Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
