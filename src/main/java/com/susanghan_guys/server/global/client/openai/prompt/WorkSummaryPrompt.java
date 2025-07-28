package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class WorkSummaryPrompt {

    public static OpenAiPrompt buildWorkSummaryPrompt() {
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

        return new OpenAiPrompt(system, user);
    }
}
