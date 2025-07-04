package com.susanghan_guys.server.globals.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.susanghan_guys.server.globals.common.code.ErrorCode;
import com.susanghan_guys.server.globals.common.code.SuccessCode;

public record ApiResponse<T>(
        boolean isSuccess,
        int code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T result
) {
    public static <T> ApiResponse<T> success(SuccessCode code, T data) {
        return new ApiResponse<>(true, code.getStatus(), code.getMessage(), data);
    }

    public static ApiResponse<String> success(SuccessCode code) {
        return new ApiResponse<>(true, code.getStatus(), code.getMessage(), "");
    }

    public static <T> ApiResponse<T> failure(ErrorCode errorCode, T data) {
        return new ApiResponse<>(false, errorCode.getStatus(), errorCode.getMessage(), data);
    }

    public static ApiResponse<Void> failure(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getStatus(), errorCode.getMessage(), null);
    }
}
