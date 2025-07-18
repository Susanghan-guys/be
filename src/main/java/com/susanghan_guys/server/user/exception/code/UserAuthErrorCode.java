package com.susanghan_guys.server.user.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserAuthErrorCode implements BaseCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 401, "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 리프레시 토큰입니다."),
    TOKEN_PARSE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "토큰 파싱에 실패했습니다."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 인증 코드 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
