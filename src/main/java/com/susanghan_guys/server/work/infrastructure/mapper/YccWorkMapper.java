package com.susanghan_guys.server.work.infrastructure.mapper;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.FilesType;
import com.susanghan_guys.server.work.dto.YccWorkSubmissionRequest;
import org.springframework.stereotype.Component;

@Component
public class YccWorkMapper {

    public Work toEntity(YccWorkSubmissionRequest dto,
                         User user,
                         Contest contest,
                         String planFileUrl) {

        return Work.builder()
                .title(dto.title())
                .filesType(FilesType.PLAN) // 기획서는 DOCUMENT 타입으로 고정
                .work(planFileUrl)
                .user(user)
                .contest(contest)
                .build();
    }
}