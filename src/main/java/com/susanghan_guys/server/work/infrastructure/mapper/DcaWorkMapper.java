package com.susanghan_guys.server.work.infrastructure.mapper;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.domain.type.ReportStatus;
import com.susanghan_guys.server.work.domain.type.WorkType;
import com.susanghan_guys.server.work.dto.request.DcaWorkSubmissionRequest;
import org.springframework.stereotype.Component;

@Component
public class DcaWorkMapper {

    public Work toEntity(DcaWorkSubmissionRequest dto,
                         User user,
                         Contest contest,
                         String briefBoardUrl) {
        return Work.builder()
                .title(dto.title())
                .number(dto.number())
                .brand(dto.brand())
                .category(dto.category())
                .type(dto.category() == Category.FILM ? WorkType.DCA_FILM : WorkType.DCA)
                .reportStatus(ReportStatus.IN_PROGRESS)
                .work(briefBoardUrl)
                .user(user)
                .contest(contest)
                .build();
    }
}