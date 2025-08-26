package com.susanghan_guys.server.feedback.presentation;

import com.susanghan_guys.server.feedback.application.FeedbackService;
import com.susanghan_guys.server.feedback.dto.request.FeedbackRequest;
import com.susanghan_guys.server.feedback.presentation.swagger.FeedbackSwagger;
import com.susanghan_guys.server.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.susanghan_guys.server.feedback.presentation.response.FeedbackResponse.FEEDBACK_SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/feedback")
public class FeedbackController implements FeedbackSwagger {

    private final FeedbackService feedbackService;

    @Override
    @PostMapping("/{workId}")
    public CommonResponse<String> createFeedback(
            @PathVariable Long workId,
            @RequestBody @Valid FeedbackRequest request
    ) {
        feedbackService.createFeedback(workId, request);
        return CommonResponse.success(FEEDBACK_SUBMIT_SUCCESS, "OK");
    }
}
