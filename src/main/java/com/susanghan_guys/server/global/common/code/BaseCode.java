package com.susanghan_guys.server.global.common.code;

import org.springframework.http.HttpStatus;

public interface BaseCode {
    HttpStatus getHttpStatus();
    int getStatus();
    String getMessage();
}
