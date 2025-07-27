package com.susanghan_guys.server.work.application.validator;

import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.Category;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DcaWorkValidator {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_WIDTH = 3508;
    private static final int MAX_HEIGHT = 4960;
    private static final String YOUTUBE_REGEX = "^https://(www\\.)?(youtube\\.com|youtu\\.be)/.+$";

    private final WorkRepository workRepository;

    public void validateBriefBoard(MultipartFile briefBoardFile) {
        if (briefBoardFile == null || briefBoardFile.isEmpty()) {
            throw new WorkException(WorkErrorCode.BRIEF_BOARD_REQUIRED);
        }

        if (!isValidJpgFile(briefBoardFile)) {
            throw new WorkException(WorkErrorCode.INVALID_BRIEF_BOARD_TYPE);
        }

        if (briefBoardFile.getSize() > MAX_FILE_SIZE) {
            throw new WorkException(WorkErrorCode.FILE_TOO_LARGE);
        }

        try {
            BufferedImage image = ImageIO.read(briefBoardFile.getInputStream());
            if (image == null || image.getWidth() > MAX_WIDTH || image.getHeight() > MAX_HEIGHT) {
                throw new WorkException(WorkErrorCode.INVALID_IMAGE_DIMENSIONS);
            }
        } catch (IOException e) {
            throw new WorkException(WorkErrorCode.INVALID_IMAGE_DIMENSIONS);
        }
    }

    public void validateAdditionalSubmission(
            Category category,
            String youtubeUrl,
            MultipartFile additionalFile
    ) {
        boolean isFilm = Category.FILM.equals(category);
        boolean hasYoutube = StringUtils.isNotBlank(youtubeUrl);
        boolean hasPlanFile = additionalFile != null && !additionalFile.isEmpty();

        if (isFilm) {
            if (!hasYoutube)
                throw new WorkException(WorkErrorCode.YOUTUBE_URL_REQUIRED);
            if (!youtubeUrl.matches(YOUTUBE_REGEX))
                throw new WorkException(WorkErrorCode.INVALID_YOUTUBE_URL);
            if (hasPlanFile && (!isValidPlanFile(additionalFile) || additionalFile.getSize() > MAX_FILE_SIZE))
                throw new WorkException(WorkErrorCode.INVALID_ADDITIONAL_FILE_TYPE);
        } else {
            if (hasYoutube)
                throw new WorkException(WorkErrorCode.YOUTUBE_NOT_ALLOWED_FOR_NON_FILM);
            if (hasPlanFile && (!isValidPlanFile(additionalFile) || additionalFile.getSize() > MAX_FILE_SIZE))
                throw new WorkException(WorkErrorCode.INVALID_ADDITIONAL_FILE_TYPE);
        }
    }

    public void validateDuplicateSubmission(Long contestId, String number) {
        Optional<Work> existing = workRepository
                .findByContestIdAndNumberAndNumberIsNotNull(contestId, number);

        if (existing.isPresent()) {
            throw new WorkException(WorkErrorCode.DUPLICATE_SUBMISSION);
        }
    }

    private boolean isValidJpgFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/jpg"));
    }

    private boolean isValidPlanFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return false;

        String contentType = file.getContentType();
        String name = file.getOriginalFilename();

        return contentType != null &&
                name != null &&
                name.matches("(?i).*\\.(pdf|ppt|pptx)$") &&
                (contentType.equals("application/pdf") ||
                        contentType.equals("application/vnd.ms-powerpoint") ||
                        contentType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation"));
    }
}