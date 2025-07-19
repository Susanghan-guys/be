package com.susanghan_guys.server.work.exception;


import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WorkException extends RuntimeException {
    private final BaseCode baseCode;
}