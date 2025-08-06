package com.susanghan_guys.server.user.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserAuthSuccessCode implements BaseCode {

    USER_LOGOUT_SUCCESS(HttpStatus.OK, 200, "User Logout Success"),

    // token
    REFRESH_TOKEN_UPDATE_SUCCESS(HttpStatus.OK, 200, "Refresh Token Update Success"),
    EXCHANGE_TOKEN_SUCCESS(HttpStatus.OK, 200, "Exchange Token Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
