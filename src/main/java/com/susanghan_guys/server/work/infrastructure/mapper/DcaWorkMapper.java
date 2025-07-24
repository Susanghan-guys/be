package com.susanghan_guys.server.work.infrastructure.mapper;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Brand;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.dto.DcaWorkSubmissionRequest;
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
                .work(briefBoardUrl)
                .user(user)
                .contest(contest)
                .build();
    }
}