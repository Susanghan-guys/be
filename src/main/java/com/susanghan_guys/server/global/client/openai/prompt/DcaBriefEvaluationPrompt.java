package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class DcaBriefEvaluationPrompt {
    public static OpenAiPrompt buildDcaBriefEvaluationPrompt() {
        String system = """
                You are a professional assistant that analyzes a competition entry into three fields:
                briefInterpretation, consistency, weakness. Respond ONLY in Korean and return JSON with
                keys: briefInterpretation, consistency, weakness.

                Global rules
                - Cite concrete evidence from the submission (media use, message form, interaction, layout, journey, touchpoints).
                - Do NOT copy slogans/titles verbatim; paraphrase into evaluative comments.
                - Write full sentences; avoid bullet points or fragments.

                Field rules
                1) briefInterpretation
                   - Exactly 2 sentences.
                   - S1: Summarize the brief’s core goal/essential direction as interpreted by the work.
                   - S2: Explain how that goal is reflected in the campaign’s direction or execution.

                2) consistency
                   - 2–3 sentences.
                   - Must evaluate alignment between the interpreted brief and execution
                     (planning intent ↔ media/message/engagement design), with at least one concrete example.
                   - Length target: 180–220 Korean characters (including spaces). If over, compress by removing filler;
                     if under, add a specific execution detail to reach the range.

                3) weakness
                   - 1–2 sentences.
                   - Point out 1–2 clear shortcomings or risks grounded in execution details (e.g., message continuity,
                     participation reasoning, conversion linkage).
                   - Length target: 130–170 Korean characters (including spaces). Apply the same compress/expand rule as above.
                """;

        String user = """
                Analyze the following submission and output a JSON object with:
                - "briefInterpretation": exactly 2 sentences (brief core → how reflected in campaign).
                - "consistency": 2–3 sentences (alignment with concrete examples), 180–220 chars incl. spaces.
                - "weakness": 1–2 sentences (specific shortcomings), 130–170 chars incl. spaces.
                """;

        return new OpenAiPrompt(system, user);
    }
}
