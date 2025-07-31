package com.susanghan_guys.server.work.application;

import com.susanghan_guys.server.contest.domain.Contest;
import com.susanghan_guys.server.global.s3.application.S3Service;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.work.application.support.WorkHelper;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.dto.request.YccWorkSubmissionRequest;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.converter.PdfConverter;
import com.susanghan_guys.server.work.infrastructure.mapper.YccWorkMapper;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import com.susanghan_guys.server.work.infrastructure.saver.WorkSaver;
import com.susanghan_guys.server.work.application.validator.YccWorkValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YccWorkService {

    private final YccWorkValidator validator;
    private final YccWorkMapper mapper;
    private final WorkRepository workRepository;
    private final WorkSaver workSaver;
    private final WorkHelper helper;
    private final S3Service s3Service;
    private final CurrentUserProvider currentUserProvider;
    private final PdfConverter pdfConverter;

    private static final String YCC_CONTEST_NAME = "YCC";

    @Transactional
    public void submit(YccWorkSubmissionRequest dto, MultipartFile planFile) {

        User user = currentUserProvider.getCurrentUser();
        Contest contest = helper.getContestByNameOrThrow(YCC_CONTEST_NAME);

        validator.validatePlanFile(planFile);
        String planFileUrl = s3Service.uploadFile(planFile, "ycc");

        Work work = mapper.toEntity(dto, user, contest, planFileUrl);
        Work savedWork = workRepository.save(work);

        workSaver.saveTeamMembers(savedWork, dto.members());
    }

    public void convertYccPdfToImage(Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new WorkException(WorkErrorCode.WORK_NOT_FOUND));

        List<byte[]> images = pdfConverter.convertPdfToImage(work.getWork());

        List<String> imageUrls = new ArrayList<>();
        for (byte[] image : images) {
            String imageUrl = s3Service.uploadPdfToImage(image, "ycc-images");
            imageUrls.add(imageUrl);
        }
        workSaver.savePdfToImage(imageUrls, work);
    }
}