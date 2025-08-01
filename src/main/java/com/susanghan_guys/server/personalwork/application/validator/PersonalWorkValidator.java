package com.susanghan_guys.server.personalwork.application.validator;

import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalWorkValidator {

    private final WorkRepository workRepository;

    public void validatePersonalWork(Long workId, User user, List<String> imageUrls) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        if (!work.getUser().equals(user)) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_OWNER_MISMATCH);
        }

        if (imageUrls.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_IMAGE_NOT_FOUND);
        }
    }
}
