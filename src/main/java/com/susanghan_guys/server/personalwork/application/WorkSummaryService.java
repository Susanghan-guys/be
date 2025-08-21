package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.client.openai.OpenAiRequest;
import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.factory.OpenAiFactory;
import com.susanghan_guys.server.personalwork.application.port.PdfFilePort;
import com.susanghan_guys.server.personalwork.application.port.OpenAiPort;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import com.susanghan_guys.server.user.domain.User;
import com.susanghan_guys.server.file.domain.PdfImage;
import com.susanghan_guys.server.work.infrastructure.persistence.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkSummaryService {

    private final CurrentUserProvider currentUserProvider;
    private final WorkRepository workRepository;
    private final OpenAiPort openAiPort;
    private final OpenAiFactory openAiFactory;
    private final PersonalWorkValidator personalWorkValidator;
    private final PdfFilePort pdfFilePort;

    public WorkSummaryResponse createDcaWorkSummary(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        List<String> imageUrls = new ArrayList<>(workRepository.findWorkContentByWorkId(workId));

        List<PdfImage> pdfImages = pdfFilePort.convertDcaPdfToImage(workId);

        // 추가 파일(기획안)이 존재할 경우, 브리프 보드 + 추가 파일(기획안) 함께 전송
        imageUrls.addAll(
                pdfImages.stream()
                        .map(PdfImage::getImageUrl)
                        .filter(Objects::nonNull)
                        .toList()
        );
        personalWorkValidator.validatePersonalWork(imageUrls);

        OpenAiRequest request = new OpenAiRequest(imageUrls);

        // TODO: DB 저장 코드 구현

        return openAiPort.createWorkSummary(request);
    }

    public WorkSummaryResponse createYccWorkSummary(Long workId) {
        User user = currentUserProvider.getCurrentUser();

        personalWorkValidator.validatePersonalWorkOwner(workId, user);

        OpenAiRequest request = openAiFactory.buildYccOpenAiRequest(workId);

        // TODO: DB 저장 코드 구현

        return openAiPort.createWorkSummary(request);
    }
}
