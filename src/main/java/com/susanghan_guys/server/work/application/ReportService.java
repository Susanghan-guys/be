package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.application.validator.ReportValidator;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.WorkVisibility;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.dto.request.ReportDeletionRequest;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkVisibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final CurrentUserProvider currentUserProvider;
    private final WorkRepository workRepository;
    private final WorkVisibilityRepository workVisibilityRepository;
    private final ReportValidator reportValidator;

    public MyReportListResponse getMyReports(String name, Integer page) {
        User user = currentUserProvider.getCurrentUser();

        Pageable pageable = PageRequest.of(
                page, 10, Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Slice<Work> works = workRepository.findAccessibleWorks(user.getId(), name, pageable);

        List<Long> workIds = works.getContent().stream()
                .map(Work::getId)
                .toList();

        Set<Long> deletableWorks = workIds.isEmpty()
                ? Collections.emptySet()
                : workRepository.findDeletableWorks(workIds, user.getId());

        return MyReportListResponse.of(works, deletableWorks);
    }

    @Transactional
    public void verifyReportCode(Long workId, ReportCodeRequest request) {
        User user = currentUserProvider.getCurrentUser();

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        reportValidator.validateReportCode(user, work, request);

        if (workVisibilityRepository.findByWorkIdAndUserId(workId, user.getId()).isPresent()) {
            return;
        }
        workVisibilityRepository.save(WorkVisibility.of(user, work, true));
    }

    @Transactional
    public void deleteReport(Long workId, ReportDeletionRequest request) {
        User user = currentUserProvider.getCurrentUser();

        reportValidator.validateDeleteReport(workId, user);

        int deletedWorks = workVisibilityRepository.deletedWorks(workId, user.getId());
        if (deletedWorks == 0) {
            throw new WorkException(WorkErrorCode.DELETABLE_WORK_NOT_FOUND);
        }
    }
}
