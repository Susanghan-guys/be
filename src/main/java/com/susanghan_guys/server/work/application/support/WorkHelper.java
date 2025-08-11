package com.susanghan_guys.server.work.application.support;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.contest.infrastructure.persistence.ContestRepository;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkHelper {

    private final ContestRepository contestRepository;

    public Contest getContestByNameOrThrow(String name) {
        return contestRepository.findByTitleIgnoreCase(name)
                .orElseThrow(() -> new WorkException(WorkErrorCode.CONTEST_NOT_FOUND));
    }
}