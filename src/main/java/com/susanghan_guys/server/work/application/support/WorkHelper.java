package com.susanghan_guys.server.work.application.support;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.contest.infrastructure.persistence.ContestRepository;
import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class WorkHelper {

    private final ContestRepository contestRepository;
    private final S3Service s3Service;

    public Contest getContestByNameOrThrow(String name) {
        return contestRepository.findByTitleIgnoreCase(name)
                .orElseThrow(() -> new WorkException(WorkErrorCode.CONTEST_NOT_FOUND));
    }
}