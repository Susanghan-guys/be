package com.susanghan_guys.server.feedback.application;

import com.susanghan_guys.server.feedback.domain.Feedback;
import com.susanghan_guys.server.feedback.dto.request.FeedbackRequest;
import com.susanghan_guys.server.feedback.infrastructure.mapper.FeedbackMapper;
import com.susanghan_guys.server.feedback.infrastructure.persistence.FeedbackRepository;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedbackService {

    private final WorkRepository workRepository;
    private final FeedbackRepository feedbackRepository;

    @Transactional
    public void createFeedback(Long workId, FeedbackRequest request) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        Feedback feedback = FeedbackMapper.toEntity(request.score(), request.content(), work);

        feedbackRepository.save(feedback);
    }
}
