package com.susanghan_guys.server.global.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements BaseCode {

    // 공통 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 내부 오류가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 403, "접근 권한이 없습니다."),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 401, "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 리프레시 토큰입니다."),
    TOKEN_PARSE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "토큰 파싱에 실패했습니다."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 인증 코드 입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 사용자입니다."),
    REQUIRED_TERMS_NOT_AGREED(HttpStatus.BAD_REQUEST, 400, "필수 약관에 동의하지 않았습니다."),
    ETC_DETAIL_REQUIRED(HttpStatus.BAD_REQUEST, 400, "기타일 경우, 상세 내용을 작성해야 합니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
