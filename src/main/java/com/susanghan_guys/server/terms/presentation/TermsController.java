package com.susanghan_guys.server.terms.presentation;

import com.susanghan_guys.server.global.common.CommonResponse;
import com.susanghan_guys.server.terms.application.TermsService;
import com.susanghan_guys.server.terms.dto.request.TermsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.global.common.code.SuccessCode.USER_AGREEMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class TermsController {

    private final TermsService termsService;

    @PostMapping("/agreement")
    public CommonResponse<String> saveUserAgreement(@RequestBody @Valid TermsRequest request) {
        termsService.saveUserAgreement(request);
        return CommonResponse.success(USER_AGREEMENT_SUCCESS, "OK");
    }
}
