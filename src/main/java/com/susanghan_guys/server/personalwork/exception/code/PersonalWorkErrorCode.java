package com.susanghan_guys.server.personalwork.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalWorkErrorCode implements BaseCode {

    WORK_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "작품을 찾을 수 없습니다."),
    WORK_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "브리프 보드 or 기획안을 찾을 수 없습니다."),
    WORK_OWNER_MISMATCH(HttpStatus.UNAUTHORIZED, 403, "작품의 소유자가 아닙니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
