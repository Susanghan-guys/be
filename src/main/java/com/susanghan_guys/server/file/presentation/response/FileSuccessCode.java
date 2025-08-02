package com.susanghan_guys.server.file.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileSuccessCode implements BaseCode {

    DCA_PDF_CONVERT_SUCCESS(HttpStatus.OK, 200, "DCA additional file converted to image successfully"),
    YCC_PDF_CONVERT_SUCCESS(HttpStatus.OK, 200, "YCC additional file converted to image successfully"),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
