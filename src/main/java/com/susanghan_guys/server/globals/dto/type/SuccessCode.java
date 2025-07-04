package com.susanghan_guys.server.globals.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // health_check
    HEALTH_CHECK_SUCCESS(200, "Health Check Success"),
    ;

    private final int status;
    private final String message;
}
