package com.susanghan_guys.server.work.exception.code;

import com.susanghan_guys.server.global.common.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WorkErrorCode implements BaseCode {

    // DCA
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "파일 업로드에 실패했습니다."),
    BRIEF_BOARD_REQUIRED(HttpStatus.BAD_REQUEST, 400, "브리프보드는 필수 제출 항목입니다."),
    INVALID_YOUTUBE_URL(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 유튜브 URL입니다."),
    INVALID_ADDITIONAL_FILE_TYPE(HttpStatus.BAD_REQUEST, 400, "추가 자료의 형식이 잘못되었습니다."),
    YOUTUBE_URL_REQUIRED(HttpStatus.BAD_REQUEST, 400, "영상 카테고리는 유튜브 링크 제출이 필수입니다."),
    YOUTUBE_NOT_ALLOWED_FOR_NON_FILM(HttpStatus.BAD_REQUEST, 400, "비영상 카테고리에는 유튜브 링크를 제출할 수 없습니다."),
    INVALID_IMAGE_DIMENSIONS(HttpStatus.BAD_REQUEST, 400, "브리프보드는 최대 3508x4960 픽셀의 JPG 파일이어야 합니다."),
    INVALID_BRIEF_BOARD_TYPE(HttpStatus.BAD_REQUEST, 400, "브리프보드는 JPG 형식이어야 합니다."),

    // YCC
    PLAN_FILE_REQUIRED(HttpStatus.BAD_REQUEST, 400, "기획서 파일은 필수입니다."),
    INVALID_PLAN_FILE_TYPE(HttpStatus.BAD_REQUEST, 400, "기획서 파일은 pdf/ppt/pptx 형식이어야 합니다."),
    FILE_TOO_LARGE(HttpStatus.BAD_REQUEST, 400, "파일 용량은 최대 10MB까지 허용됩니다."),
    ADDITIONAL_FILE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "추가 파일을 찾을 수 없습니다."),

    // BOTH
    DUPLICATE_SUBMISSION(HttpStatus.CONFLICT, 409, "이미 제출한 작품입니다."),
    CONTEST_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 공모전입니다."),
    WORK_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 작품입니다."),
    PDF_TO_IMAGE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "PDF 파일을 이미지로 변환하는 중 오류가 발생했습니다."),
    INVALID_REPORT_CODE(HttpStatus.FORBIDDEN, 403, "유효하지 않은 인증 코드입니다."),
    APPLICANTS_NOT_CODE_VERIFIED(HttpStatus.FORBIDDEN, 403, "신청자는 리포트 코드 인증이 불가능합니다."),
    APPLICANTS_NOT_DELETED(HttpStatus.FORBIDDEN, 403, "신청자는 리포트를 삭제할 수 없습니다."),
    DELETABLE_WORK_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "삭제 가능한 리포트가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;
}