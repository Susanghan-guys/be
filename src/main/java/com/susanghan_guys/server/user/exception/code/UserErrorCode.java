package com.susanghan_guys.server.user.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 사용자입니다."),
    REQUIRED_TERMS_NOT_AGREED(HttpStatus.BAD_REQUEST, 400, "필수 약관에 동의하지 않았습니다."),
    ETC_DETAIL_REQUIRED(HttpStatus.BAD_REQUEST, 400, "기타일 경우, 상세 내용을 작성해야 합니다."),
    WITHDRAWAL_REASON_REQUIRED(HttpStatus.BAD_REQUEST, 400, "탈퇴 이유가 선택되지 않았습니다."),
    USER_ALREADY_DELETED(HttpStatus.CONFLICT, 409, "이미 탈퇴한 사용자 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
