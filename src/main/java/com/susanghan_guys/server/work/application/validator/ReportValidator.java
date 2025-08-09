package com.susanghan_guys.server.work.application.validator;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.dto.request.ReportDeletionRequest;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkVisibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportValidator {

    private final WorkRepository workRepository;
    private final WorkVisibilityRepository workVisibilityRepository;

    public void validateReportCode(User user, Work work, ReportCodeRequest request) {
        if (work.getUser().getId().equals(user.getId())) {
            throw new WorkException(WorkErrorCode.APPLICANTS_NOT_CODE_VERIFIED);
        }

        if (work.getCode() == null || request.code() == null || !request.code().trim().equals(work.getCode())) {
            throw new WorkException(WorkErrorCode.INVALID_REPORT_CODE);
        }
    }

    public void validateDeleteReport(Long workId, User user, ReportDeletionRequest request) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        if (work.getUser().getId().equals(user.getId())) {
            throw new WorkException(WorkErrorCode.APPLICANTS_NOT_DELETED);
        }

        if (!request.title().equals(work.getTitle())) {
            throw new WorkException(WorkErrorCode.WORK_NOT_FOUND);
        }

        if (!workVisibilityRepository.existsByWorkIdAndUserIdAndVisibleTrue(workId, user.getId())) {
            throw new WorkException(WorkErrorCode.DELETABLE_WORK_NOT_FOUND);
        }
    }
}
