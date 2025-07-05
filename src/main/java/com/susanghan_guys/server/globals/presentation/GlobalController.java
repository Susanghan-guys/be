package com.susanghan_guys.server.globals.presentation;

import com.susanghan_guys.server.globals.common.CommonResponse;
import com.susanghan_guys.server.globals.presentation.swagger.GlobalSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.susanghan_guys.server.globals.common.code.SuccessCode.HEALTH_CHECK_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/global")
public class GlobalController implements GlobalSwagger {

    @Override
    @GetMapping("/health-check")
    public ResponseEntity<CommonResponse<String>> healthCheck(){
        return ResponseEntity.ok(CommonResponse.success(HEALTH_CHECK_SUCCESS, "OK"));
    }
}
