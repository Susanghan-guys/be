package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.application.support.WorkHelper;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.type.FilesType;
import com.susanghan_guys.server.work.dto.DcaWorkSubmissionRequest;
import com.susanghan_guys.server.work.infrastructure.mapper.DcaWorkMapper;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.work.infrastructure.saver.WorkSaver;
import com.susanghan_guys.server.work.validator.DcaWorkValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DcaWorkService {

    private final DcaWorkValidator validator;
    private final DcaWorkMapper mapper;
    private final WorkRepository workRepository;
    private final WorkSaver workSaver;
    private final WorkHelper helper;
    private final CurrentUserProvider currentUserProvider;
    private final S3Service s3Service;

    private static final String DCA_CONTEST_NAME = "DCA";

    public void submit(DcaWorkSubmissionRequest dto,
                       MultipartFile briefBoardFile,
                       MultipartFile additionalFile) {

        User user = currentUserProvider.getCurrentUser();
        Contest contest = helper.getContestByNameOrThrow(DCA_CONTEST_NAME);

        validator.validateDuplicateSubmission(contest.getId(), dto.number());
        validator.validateBriefBoard(briefBoardFile);
        validator.validateAdditionalSubmission(additionalFile, dto.youtubeUrl(), dto.category());

        String briefBoardUrl = s3Service.uploadFile(briefBoardFile, "dca");
        Work work = mapper.toEntity(dto, user, contest, briefBoardUrl);
        Work savedWork = workRepository.save(work);

        workSaver.saveTeamMembers(savedWork, dto.members());

        FilesType filesType = FilesType.valueOf(dto.filesType().toUpperCase());
        String uploadedAdditionalUrl = null;

        if (additionalFile != null && !additionalFile.isEmpty()) {
            uploadedAdditionalUrl = s3Service.uploadFile(additionalFile, "dca");
        }
        workSaver.saveAdditionalFiles(savedWork, dto.youtubeUrl(), additionalFile, filesType, uploadedAdditionalUrl);
    }
}