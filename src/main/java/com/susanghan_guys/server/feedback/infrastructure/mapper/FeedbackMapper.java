package com.susanghan_guys.server.feedback.infrastructure.mapper;

import com.susanghan_guys.server.feedback.domain.Feedback;
import com.susanghan_guys.server.work.domain.Work;

public class FeedbackMapper {

    public static Feedback toEntity(Integer score, String content, Work work) {
        return Feedback.builder()
                .score(score)
                .content(content)
                .work(work)
                .build();
    }
}
