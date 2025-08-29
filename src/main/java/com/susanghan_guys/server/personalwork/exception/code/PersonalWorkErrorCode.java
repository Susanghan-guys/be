package com.susanghan_guys.server.personalwork.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalWorkErrorCode implements BaseCode {

    WORK_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "작품을 찾을 수 없습니다."),
    WORK_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "브리프 보드 or 기획안을 찾을 수 없습니다."),
    WORK_OWNER_MISMATCH(HttpStatus.FORBIDDEN, 403, "작품의 소유자가 아닙니다."),
    EVALUATION_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "작품 전체 총평 타입을 찾을 수 없습니다."),
    SUMMARY_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "출품작 요약을 찾을 수 없습니다."),
    DETAIL_EVALUATION_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "해당 타입의 세부 총평을 찾을 수 없습니다."),
    BRIEF_ANALYSIS_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "브리프 해석을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
