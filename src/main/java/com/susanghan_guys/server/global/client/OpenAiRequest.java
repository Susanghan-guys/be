package com.susanghan_guys.server.global.client;

import java.util.List;

public record OpenAiRequest(
        List<String> imageUrls
) {
}
