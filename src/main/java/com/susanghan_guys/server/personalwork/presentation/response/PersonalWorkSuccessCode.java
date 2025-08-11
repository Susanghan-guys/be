package com.susanghan_guys.server.personalwork.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalWorkSuccessCode implements BaseCode {

    DCA_WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Dca Work Summary Success"),
    YCC_WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Ycc Work Summary Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
