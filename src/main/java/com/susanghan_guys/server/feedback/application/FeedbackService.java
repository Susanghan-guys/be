package com.susanghan_guys.server.feedback.application;

import com.susanghan_guys.server.feedback.domain.Feedback;
import com.susanghan_guys.server.feedback.dto.request.FeedbackRequest;
import com.susanghan_guys.server.feedback.exception.FeedbackException;
import com.susanghan_guys.server.feedback.exception.code.FeedbackErrorCode;
import com.susanghan_guys.server.feedback.infrastructure.mapper.FeedbackMapper;
import com.susanghan_guys.server.feedback.infrastructure.persistence.FeedbackRepository;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
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
    private final CurrentUserProvider currentUserProvider;

    @Transactional
    public void createFeedback(Long workId, FeedbackRequest request) {
        User user = currentUserProvider.getCurrentUser();

        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        if (feedbackRepository.existsByWorkAndUser(work, user)) {
            throw new FeedbackException(FeedbackErrorCode.FEEDBACK_ALREADY_EXIST);
        }

        Feedback feedback = FeedbackMapper.toEntity(request.score(), request.content(), user, work);

        feedbackRepository.save(feedback);
    }
}
