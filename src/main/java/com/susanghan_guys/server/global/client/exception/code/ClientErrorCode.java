package com.susanghan_guys.server.global.client.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClientErrorCode implements BaseCode {

    UNKNOWN_IMAGE_TYPE(HttpStatus.BAD_REQUEST, 400, "지원하지 않는 이미지 형식입니다."),
    INVALID_IMAGE_URL(HttpStatus.BAD_REQUEST, 400, "잘못된 이미지 URL입니다."),
    IMAGE_URL_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "이미지 URL이 추출되지 않았습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
