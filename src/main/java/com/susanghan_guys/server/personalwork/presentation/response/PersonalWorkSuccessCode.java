package com.susanghan_guys.server.personalwork.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalWorkSuccessCode implements BaseCode {

    WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Work Summary Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
