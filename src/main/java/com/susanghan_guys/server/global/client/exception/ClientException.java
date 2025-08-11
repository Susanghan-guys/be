package com.susanghan_guys.server.global.client.exception;

import com.susanghan_guys.server.global.client.exception.code.ClientErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
import lombok.Getter;

@Getter
public class ClientException extends BusinessException {
    public ClientException(ClientErrorCode errorCode) {
        super(errorCode);
    }
}
