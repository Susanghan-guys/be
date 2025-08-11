package com.susanghan_guys.server.work.application.validator;

import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class YccWorkValidator {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    public void validatePlanFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new WorkException(WorkErrorCode.PLAN_FILE_REQUIRED);
        }

        String filename = file.getOriginalFilename();
        if (filename == null || !filename.matches("(?i).*\\.(pdf|ppt|pptx)$")) {
            throw new WorkException(WorkErrorCode.INVALID_PLAN_FILE_TYPE);
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new WorkException(WorkErrorCode.FILE_TOO_LARGE);
        }
    }

}