package com.susanghan_guys.server.personalwork.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalWorkErrorCode implements BaseCode {

    WORK_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "작품을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
