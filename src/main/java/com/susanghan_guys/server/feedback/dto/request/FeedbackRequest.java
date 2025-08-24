package com.susanghan_guys.server.feedback.dto.request;

import lombok.Builder;

@Builder
public record FeedbackRequest(
        Integer score,
        String content
) {
}
