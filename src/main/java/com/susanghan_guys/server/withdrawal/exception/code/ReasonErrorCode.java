package com.susanghan_guys.server.withdrawal.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReasonErrorCode implements BaseCode {

    ETC_DETAIL_REQUIRED(HttpStatus.BAD_REQUEST, 400, "기타일 경우, 상세 내용을 작성해야 합니다."),
    WITHDRAWAL_REASON_REQUIRED(HttpStatus.BAD_REQUEST, 400, "탈퇴 이유가 선택되지 않았습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
