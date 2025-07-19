package com.susanghan_guys.server.work.infrastructure.saver;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.susanghan_guys.server.work.domain.AdditionalFile;
import com.susanghan_guys.server.work.domain.Member;
import com.susanghan_guys.server.work.domain.Work;
import com.susanghan_guys.server.work.domain.WorkMember;
import com.susanghan_guys.server.work.domain.type.FilesType;
import com.susanghan_guys.server.work.dto.TeamMemberDto;
import com.susanghan_guys.server.work.exception.WorkException;
import com.susanghan_guys.server.work.exception.code.WorkErrorCode;
import com.susanghan_guys.server.work.infrastructure.persistence.AdditionalFileRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.MemberRepository;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkSaver {

    private final MemberRepository memberRepository;
    private final WorkMemberRepository workMemberRepository;
    private final AdditionalFileRepository additionalFileRepository;

    public void saveTeamMembers(Work work, List<TeamMemberDto> members) {
        if (members == null || members.isEmpty()) return;

        for (TeamMemberDto dto : members) {
            Member member = memberRepository.findByEmail(dto.email())
                    .orElseGet(() -> memberRepository.save(new Member(dto.name(), dto.email())));
            workMemberRepository.save(new WorkMember(work, member));
        }
    }

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
        }
    }
}