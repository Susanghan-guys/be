package com.susanghan_guys.server.personalwork.exception;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import lombok.Getter;

@Getter
public class PersonalWorkException extends BusinessException {
    public PersonalWorkException(PersonalWorkErrorCode errorCode) {
        super(errorCode);
    }
}
