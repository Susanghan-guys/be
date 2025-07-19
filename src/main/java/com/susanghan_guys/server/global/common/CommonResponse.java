package com.susanghan_guys.server.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.susanghan_guys.server.global.common.code.BaseCode;
import com.susanghan_guys.server.global.common.code.SuccessCode;

public record CommonResponse<T>(
        boolean isSuccess,
        int code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T result
) {
    public static <T> CommonResponse<T> success(SuccessCode code, T data) {
        return new CommonResponse<>(true, code.getStatus(), code.getMessage(), data);
    }

    public static CommonResponse<String> success(SuccessCode code) {
        return new CommonResponse<>(true, code.getStatus(), code.getMessage(), "");
    }

    public static <T> CommonResponse<T> failure(BaseCode errorCode, T data) {
        return new CommonResponse<>(false, errorCode.getStatus(), errorCode.getMessage(), data);
    }

    public static CommonResponse<Void> failure(BaseCode errorCode) {
        return new CommonResponse<>(false, errorCode.getStatus(), errorCode.getMessage(), null);
    }
}
