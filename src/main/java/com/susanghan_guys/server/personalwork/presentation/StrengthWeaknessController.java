package com.susanghan_guys.server.personalwork.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.personalwork.application.StrengthWeaknessService;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.presentation.swagger.StrengthWeaknessSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.susanghan_guys.server.personalwork.presentation.response.PersonalWorkSuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/personal-works")
public class StrengthWeaknessController implements StrengthWeaknessSwagger {

    private final StrengthWeaknessService strengthWeaknessService;

    @GetMapping("/{workId}/strengths")
    public CommonResponse<List<DetailEvaluationResponse.DetailEvaluation>> getStrengths(@PathVariable Long workId) {
        return CommonResponse.success(WORK_STRENGTHS_SUCCESS, strengthWeaknessService.getStrengths(workId));
    }

    @GetMapping("/{workId}/weaknesses")
    public CommonResponse<List<DetailEvaluationResponse.DetailEvaluation>> getWeaknesses(@PathVariable Long workId) {
        return CommonResponse.success(WORK_WEAKNESSES_SUCCESS, strengthWeaknessService.getWeaknesses(workId));
    }
}