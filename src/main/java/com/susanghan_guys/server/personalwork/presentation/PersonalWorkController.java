package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.PersonalWorkService;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.WORK_SUMMARY_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-work")
public class PersonalWorkController {

    private final PersonalWorkService personalWorkService;

    @PostMapping("/summary")
    public CommonResponse<WorkSummaryResponse> createWorkSummary(@RequestBody @Valid OpenAiRequest request) {
        return CommonResponse.success(WORK_SUMMARY_SUCCESS, personalWorkService.createWorkSummary(request));
    }
}
