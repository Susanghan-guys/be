package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.global.exception.BusinessException;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.ReportVisibility;
import com.susanghan_guys.server.work.dto.request.ReportCodeRequest;
import com.susanghan_guys.server.work.dto.response.MyReportListResponse;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final CurrentUserProvider currentUserProvider;
    private final WorkRepository workRepository;

    public MyReportListResponse getMyReports(String name, Integer page) {
        User user = currentUserProvider.getCurrentUser();

        Pageable pageable = PageRequest.of(
                page, 10, Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Slice<Work> works;

        if (name == null || name.isBlank()) {
            works = workRepository.findByUser(user, pageable);
        } else {
            works = workRepository.findByUserAndContest_Title(user, name, pageable);
        }

        return MyReportListResponse.from(works);
    }

    public void verifyReportCode(Long workId, ReportCodeRequest request) {
        currentUserProvider.getCurrentUser();

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new BusinessException(WorkErrorCode.WORK_NOT_FOUND));

        if (!request.code().equals(work.getCode())) {
            throw new WorkException(WorkErrorCode.WORK_NOT_FOUND); // TODO: errorCode 수정
        }

        work.getWorkMembers()
                .forEach(workMember -> workMember.updateVisibility(ReportVisibility.VISIBLE));
    }
}
