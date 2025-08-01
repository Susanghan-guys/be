package com.susanghan_guys.server.work.infrastructure.saver;

import com.susanghan_guys.server.work.domain.*;
import com.susanghan_guys.server.work.domain.type.FilesType;
import com.susanghan_guys.server.work.domain.type.SourceType;
import com.susanghan_guys.server.work.dto.response.TeamMemberResponse;
import com.susanghan_guys.server.work.infrastructure.mapper.PdfFileMapper;
import com.susanghan_guys.server.work.infrastructure.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkSaver {

    private final TeamMemberRepository teamMemberRepository;
    private final WorkMemberRepository workMemberRepository;
    private final AdditionalFileRepository additionalFileRepository;
    private final PdfImageRepository pdfImageRepository;
    private final PdfFileRepository pdfFileRepository;

    @Transactional
    public void saveTeamMembers(Work work, List<TeamMemberResponse> teamMembers) {
        if (teamMembers == null || teamMembers.isEmpty()) return;

        for (TeamMemberResponse dto : teamMembers) {
            TeamMember teamMember = teamMemberRepository.findByEmail(dto.email())
                    .orElseGet(() -> teamMemberRepository.save(new TeamMember(dto.name(), dto.email())));
            workMemberRepository.save(new WorkMember(work, teamMember));
        }
    }

    @Transactional
    public void saveAdditionalFiles(
            Work work,
            String youtubeUrl,
            MultipartFile additionalFile,
            String uploadedUrl
    ) {
        List<AdditionalFile> additionalFiles = new ArrayList<>();

        if (StringUtils.isNotBlank(youtubeUrl)) {
            additionalFiles.add(AdditionalFile.builder()
                    .work(work)
                    .type(FilesType.VIDEO)
                    .value(youtubeUrl)
                    .build());
        }

        if (additionalFile != null && !additionalFile.isEmpty()) {
            additionalFiles.add(AdditionalFile.builder()
                    .work(work)
                    .type(FilesType.PLAN)
                    .value(uploadedUrl)
                    .build());
        }

        if (!additionalFiles.isEmpty()) {
            additionalFileRepository.saveAll(additionalFiles);
            pdfFileRepository.save(new PdfFile(uploadedUrl, SourceType.ADDITIONAL_FILE, work.getId()));
        }
    }

    @Transactional
    public void savePdfToImage(List<String> imageUrls, PdfFile pdfFile) {
        for (String imageUrl : imageUrls) {
            pdfImageRepository.save(new PdfImage(imageUrl, pdfFile));
        }
    }
}