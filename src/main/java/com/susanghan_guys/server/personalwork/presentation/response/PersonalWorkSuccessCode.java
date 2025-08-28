package com.susanghan_guys.server.personalwork.presentation.response;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalWorkSuccessCode implements BaseCode {

    DCA_WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Dca Work Summary Success"),
    YCC_WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Ycc Work Summary Success"),

    YCC_WORK_EVALUATION_SUCCESS(HttpStatus.OK, 200, "Ycc Work Evaluation Success"),
    YCC_WORK_DETAIL_EVALUATION_SUCCESS(HttpStatus.OK, 200, "Ycc Work Detail Evaluation Success"),

    DCA_WORK_EVALUATION_SUCCESS(HttpStatus.OK, 200, "Dca Work Evaluation Success"),
    DCA_WORK_DETAIL_EVALUATION_SUCCESS(HttpStatus.OK, 200, "Dca Work Detail Evaluation Success"),
    DCA_BRIEF_EVALUATION_SUCCESS(HttpStatus.OK, 200, "Dca Brief Evaluation Success"),

    WORK_STRENGTHS_SUCCESS(HttpStatus.OK, 200, "Work Strengths Retrieval Success"),
    WORK_WEAKNESSES_SUCCESS(HttpStatus.OK, 200, "Work Weaknesses Retrieval Success"),

    WORK_DETAILS_FETCH_SUCCESS(HttpStatus.OK, 200, "Work Detail Evaluation Retrieval Success"),
    WORK_SUMMARY_SUCCESS(HttpStatus.OK, 200, "Work Summary Retrieval Success")
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}
