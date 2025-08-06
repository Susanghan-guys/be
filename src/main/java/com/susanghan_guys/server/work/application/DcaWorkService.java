package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.application.support.WorkHelper;
import com.susanghan_guys.server.work.domain.AdditionalFile;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.dto.request.DcaWorkSubmissionRequest;
import com.susanghan_guys.server.work.infrastructure.mapper.DcaWorkMapper;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.file.infrastructure.saver.PdfFileSaver;
import com.susanghan_guys.server.work.infrastructure.saver.WorkSaver;
import com.susanghan_guys.server.work.application.validator.DcaWorkValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DcaWorkService {

    private final DcaWorkValidator validator;
    private final DcaWorkMapper mapper;
    private final WorkRepository workRepository;
    private final WorkSaver workSaver;
    private final PdfFileSaver pdfFileSaver;
    private final WorkHelper helper;
    private final CurrentUserProvider currentUserProvider;
    private final S3Service s3Service;

    private static final String DCA_CONTEST_NAME = "DCA";

    @Transactional
    public void submit(DcaWorkSubmissionRequest dto,
                       MultipartFile briefBoardFile,
                       MultipartFile additionalFile) {

        User user = currentUserProvider.getCurrentUser();
        Contest contest = helper.getContestByNameOrThrow(DCA_CONTEST_NAME);

        validator.validateDuplicateSubmission(contest.getId(), dto.number());
        validator.validateBriefBoard(briefBoardFile);

        // hasVideo 제거 → youtubeUrl 유무로 판단
        validator.validateAdditionalSubmission(
                dto.category(),
                dto.youtubeUrl(),
                additionalFile
        );

        String briefBoardUrl = s3Service.uploadFile(briefBoardFile, "dca");
        Work work = mapper.toEntity(dto, user, contest, briefBoardUrl);
        Work savedWork = workRepository.save(work);
        workSaver.saveTeamMembers(savedWork, dto.teamMembers());

        String uploadedAdditionalUrl = null;
        if (additionalFile != null && !additionalFile.isEmpty()) {
            uploadedAdditionalUrl = s3Service.uploadFile(additionalFile, "dca");
        }

        // hasVideo 제거 → youtubeUrl 로 판단
        List<AdditionalFile> savedFiles = workSaver.saveAdditionalFiles(
                savedWork,
                dto.youtubeUrl(),
                additionalFile,
                uploadedAdditionalUrl
        );
        savedFiles.forEach(pdfFileSaver::savePdfFile);
    }
}