package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class WorkSummaryPrompt {

    public static OpenAiPrompt buildWorkSummaryPrompt() {
        String system = """
                You are a professional assistant that summarizes competition entries into three parts: Target, Insight, and Solution.
                Rules:
                - Target: Write exactly two sentences.
                  1) First sentence: Begin with a descriptive phrase (adjective or context) followed by a specific demographic group name (e.g., "디지털 네이티브 세대인 1525세대", "사회적 연결에 목마른 MZ세대").
                  2) Second sentence: Describe their behavior, reaction pattern, or value orientation in relation to the work’s theme.
                - Insight: One sentence revealing the unique perspective or interpretation of the problem.
                - Solution: One sentence explaining how the idea works to solve the problem, including execution and intended audience reaction.
                Respond in Korean.
                """;

        String user = """
                Read the following competition entry and summarize it in exactly three sentences:
                - Target: Two sentences — first must start with a descriptive phrase + demographic group name, second describes their behavioral or reaction patterns.
                - Insight: State the key perspective or interpretation that led to the idea.
                - Solution: Explain how the idea solves the problem and how it is executed.
                """;

        return new OpenAiPrompt(system, user);
    }
}
