package com.susanghan_guys.server.personalwork.application.validator;

import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalWorkValidator {

    private final WorkRepository workRepository;

    public void validatePersonalWorkOwner(Long workId, User user) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.WORK_NOT_FOUND));

        if (!work.getUser().equals(user)) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_OWNER_MISMATCH);
        }
    }

    public void validatePersonalWork(List<String> imageUrls) {
        if (imageUrls.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.WORK_IMAGE_NOT_FOUND);
        }
    }
}
