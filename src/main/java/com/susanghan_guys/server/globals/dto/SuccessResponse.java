package com.susanghan_guys.server.globals.dto;

import com.susanghan_guys.server.globals.dto.type.SuccessCode;

public record SuccessResponse<T>(
        int status,
        String message,
        T data
) {
    private static final String NOTHING = "";

    public static SuccessResponse<String> of(SuccessCode code) {
        return new SuccessResponse<>(code.getStatus(), code.getMessage(), NOTHING);
    }

    public static <T> SuccessResponse<T> of(SuccessCode code, T data) {
        return new SuccessResponse<>(code.getStatus(), code.getMessage(), data);
    }
}
