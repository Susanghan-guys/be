package com.susanghan_guys.server.global.client.openai.type;

import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ImageMimeType {
    PNG("png", MimeTypeUtils.IMAGE_PNG),
    JPEG("jpeg", MimeTypeUtils.IMAGE_JPEG),
    JPG("jpg", MimeTypeUtils.IMAGE_JPEG);

    private final String extension;
    private final MimeType mimeType;

    private static final Map<String, MimeType> EXTENSION_TO_MIME_TYPE;

    static {
        EXTENSION_TO_MIME_TYPE = Arrays.stream(values())
                .collect(Collectors.toMap(type -> type.extension, type -> type.mimeType));
    }

    ImageMimeType(String extension, MimeType mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }

    public static MimeType fromExtension(String extension) {
        String lowerExtension = extension.toLowerCase();
        MimeType mimeType = EXTENSION_TO_MIME_TYPE.get(lowerExtension);

        if (mimeType == null) {
            throw new IllegalArgumentException("Unknown image mime type: " + extension);
        }
        return mimeType;
    }
}
