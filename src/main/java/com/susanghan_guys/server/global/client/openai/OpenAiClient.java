package com.susanghan_guys.server.global.client.openai;

import com.susanghan_guys.server.global.client.openai.type.ImageMimeType;
import com.susanghan_guys.server.global.common.code.ErrorCode;
import com.susanghan_guys.server.global.exception.BusinessException;
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
                        throw new BusinessException(ErrorCode.BAD_REQUEST);
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
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }
        return fileName.substring(dotIndex + 1);
    }
}
