package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.application.support.WorkHelper;
import com.susanghan_guys.server.work.domain.AdditionalFile;
import com.susanghan_guys.server.work.domain.PdfFile;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.dto.request.DcaWorkSubmissionRequest;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.converter.PdfConverter;
import com.susanghan_guys.server.work.infrastructure.mapper.DcaWorkMapper;
import com.susanghan_guys.server.work.infrastructure.persistence.AdditionalFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.PdfFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.work.infrastructure.saver.WorkSaver;
import com.susanghan_guys.server.work.application.validator.DcaWorkValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DcaWorkService {

    private final DcaWorkValidator validator;
    private final DcaWorkMapper mapper;
    private final WorkRepository workRepository;
    private final WorkSaver workSaver;
    private final WorkHelper helper;
    private final CurrentUserProvider currentUserProvider;
    private final S3Service s3Service;
    private final AdditionalFileRepository additionalFileRepository;
    private final PdfConverter pdfConverter;
    private final PdfFileRepository pdfFileRepository;

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
        workSaver.saveAdditionalFiles(
                savedWork,
                dto.youtubeUrl(),
                additionalFile,
                uploadedAdditionalUrl
        );
    }

    @Transactional
    public void convertDcaPdfToImage(Long workId) {
        AdditionalFile additionalFile = additionalFileRepository.findAdditionalFileByWorkId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.ADDITIONAL_FILE_NOT_FOUND));

        PdfFile pdfFile = pdfFileRepository.findBySourceId(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<byte[]> images = pdfConverter.convertPdfToImage(additionalFile.getValue());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, "dca-images");
            imageUrls.add(imageUrl);
        }
        workSaver.savePdfToImage(imageUrls, pdfFile);
    }
}