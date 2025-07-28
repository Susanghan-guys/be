package com.susanghan_guys.server.global.client.openai;

import com.susanghan_guys.server.global.client.exception.ClientException;
import com.susanghan_guys.server.global.client.exception.code.ClientErrorCode;
import com.susanghan_guys.server.global.client.openai.type.ImageMimeType;
import com.susanghan_guys.server.personalwork.dto.response.WorkSummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import java.net.MalformedURLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiClient {

    private final ChatClient chatClient;

    public WorkSummaryResponse createWorkSummary(OpenAiRequest request) {
        String system = """
                You are a professional assistant that summarizes competition entries in a structured format.
                Always categorize the summary into three parts: Target, Insight, and Solution.
                Each category must be summarized in exactly one concise sentence.
                Respond in Korean.
                """;

        String user = """
                Read the following competition entry and summarize it.
                
                - Target: Describe the target audience or problem of this work in one sentence.
                - Insight: Summarize the core insight or key idea in one sentence.
                - Solution: Summarize the solution this work provides in one sentence.
                """;
        OpenAiPrompt prompt = new OpenAiPrompt(system, user);
        return callWithStructuredOutput(request, prompt, WorkSummaryResponse.class);
    }

    public <T> T callWithStructuredOutput(
            OpenAiRequest request,
            OpenAiPrompt prompt,
            Class<T> clazz
    ) {
        BeanOutputConverter<T> outputConverter = new BeanOutputConverter<>(clazz);

        String response = chatClient.prompt()
                .user(promptUserSpec -> {
                    try {
                        promptUserSpec.text(prompt.user() + outputConverter.getFormat());

                        for (String imageUrl : request.imageUrls()) {
                            MimeType mimeType = ImageMimeType.fromExtension(extractFileExtension(imageUrl));
                            promptUserSpec.media(mimeType, new UrlResource(imageUrl));
                        }
                    } catch (MalformedURLException e) {
                        log.error("🚨 MalformedException 발생: {}", e.getMessage());
                        throw new ClientException(ClientErrorCode.INVALID_IMAGE_URL);
                    }
                })
                .system(prompt.system())
                .call()
                .content();

        return outputConverter.convert(response);
    }

    private String extractFileExtension(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new ClientException(ClientErrorCode.IMAGE_URL_NOT_FOUND);
        }
        return fileName.substring(dotIndex + 1);
    }
}
