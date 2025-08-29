package com.susanghan_guys.server.personalwork.application.validator;

import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.EvaluationRepository;
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
    private final EvaluationRepository evaluationRepository;

    public boolean isOwner(Long workId, Long userId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new PersonalWorkException(PersonalWorkErrorCode.WORK_NOT_FOUND));

        return work.getUser().getId().equals(userId);
    }

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

    public void validateEvaluationExists(Long workId) {
        if (!evaluationRepository.existsByWorkId(workId)) {
            throw new PersonalWorkException(PersonalWorkErrorCode.EVALUATION_NOT_FOUND);
        }
    }
}
