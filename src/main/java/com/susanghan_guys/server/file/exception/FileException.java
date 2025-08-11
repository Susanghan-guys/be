package com.susanghan_guys.server.file.exception;

import com.susanghan_guys.server.file.exception.code.FileErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import lombok.Getter;

@Getter
public class FileException extends BusinessException {
    public FileException(FileErrorCode errorCode) {
        super(errorCode);
    }
}
