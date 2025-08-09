package com.susanghan_guys.server.work.application.validator;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportValidator {

    private final WorkRepository workRepository;

    public void validateReportCode(User user, Work work, ReportCodeRequest request) {
        if (work.getUser().getId().equals(user.getId())) {
            throw new WorkException(WorkErrorCode.APPLICANTS_NOT_CODE_VERIFIED);
        }

        if (!request.code().equals(work.getCode())) {
            throw new WorkException(WorkErrorCode.INVALID_REPORT_CODE);
        }
    }

    public void validateDeleteReport(Long workId, User user) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new BusinessException(WorkErrorCode.WORK_NOT_FOUND));

        if (work.getUser().getId().equals(user.getId())) {
            throw new WorkException(WorkErrorCode.APPLICANTS_NOT_DELETED);
        }
    }
}
