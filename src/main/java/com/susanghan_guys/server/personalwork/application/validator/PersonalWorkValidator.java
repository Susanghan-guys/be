package com.susanghan_guys.server.personalwork.application.validator;

import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonalWorkValidator {

    public void validatePersonalWork(List<String> imageUrls) {
        if (imageUrls.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_NOT_FOUND);
        }
    }
}
