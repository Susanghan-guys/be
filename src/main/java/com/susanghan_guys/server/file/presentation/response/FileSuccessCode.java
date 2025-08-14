package com.susanghan_guys.server.file.presentation.response;


import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileSuccessCode implements BaseCode {

    DCA_CONVERT_SUCCESS(HttpStatus.OK, 200, "DCA Pdf To Image Covert Success"),
    YCC_CONVERT_SUCCESS(HttpStatus.OK, 200, "YCC Pdf To Image Covert Success"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
