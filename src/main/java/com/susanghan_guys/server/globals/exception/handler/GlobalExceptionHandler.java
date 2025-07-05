package com.susanghan_guys.server.globals.exception.handler;

import com.susanghan_guys.server.globals.common.CommonResponse;
import com.susanghan_guys.server.globals.common.code.ErrorCode;
import com.susanghan_guys.server.globals.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<CommonResponse<Void>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode =  e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(CommonResponse.failure(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ErrorCode.BAE_REQUEST.getHttpStatus())
                .body(CommonResponse.failure(ErrorCode.BAE_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Void>> handleUnexpectedException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
