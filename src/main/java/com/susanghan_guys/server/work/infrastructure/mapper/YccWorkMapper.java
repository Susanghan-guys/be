package com.susanghan_guys.server.work.infrastructure.mapper;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import com.susanghan_guys.server.work.domain.type.WorkType;
import com.susanghan_guys.server.work.dto.request.YccWorkSubmissionRequest;
import org.springframework.stereotype.Component;

@Component
public class YccWorkMapper {

    public Work toEntity(YccWorkSubmissionRequest dto,
                         User user,
                         Contest contest,
                         String planFileUrl) {

        return Work.builder()
                .title(dto.title())
                .work(planFileUrl)
                .type(WorkType.YCC)
                .reportStatus(ReportStatus.IN_PROGRESS)
                .user(user)
                .contest(contest)
                .build();
    }
}