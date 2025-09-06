package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class DcaBriefEvaluationPrompt {
    public static OpenAiPrompt buildDcaBriefEvaluationPrompt(DcaOpenAiRequest.BrandBriefPayload brief) {
        String system = """
                You are a professional assistant that analyzes a competition entry into three fields:
                interpretation, consistency. Respond ONLY in Korean and return JSON with
                keys: interpretation, consistency.
                
                Global rules
                - Always output all three fields.
                - Use the Brand Brief as primary guidance if provided.
                - Cite concrete evidence from the submission (media use, message form, interaction, layout, journey, touchpoints).
                - Do NOT copy slogans/titles verbatim; paraphrase into evaluative comments.
                - End every sentence with "~한다", "~이다", "~하고 있다" (avoid "~습니다", "~임", "~했음").
                
                Brand Brief usage
                - If a Brand Brief is provided, your analysis MUST anchor to it.
                - Reference the brief’s core goal, value, tone/positioning, or category context as the basis of judgment.
                - Do NOT invent new brand assets or proper nouns not present in the submission or brief.
                - If no brief is provided, infer the likely intent from the submission and state it implicitly.

                Field rules
                1) interpretation
                - Exactly 2 sentences.
                - Structure: [the brief’s most essential goal or directional core] + [how that goal is reflected in the campaign].
                - The second sentence MUST tie the brief’s core to a concrete executional choice in the work.

                2) consistency
                - 3–4 sentences.
                - Evaluate alignment between the interpreted brief and execution (planning intent ↔ media/message/engagement design).
                - Include at least one concrete example referencing story flow, media characteristics, or participation design.
                - Focus on how consistently the brief’s core direction is carried through—not on micro design polish.
                """;

        String briefBlock = (brief == null) ? "" : """
                [Brand Brief]
                - brandIntro: %s
                - marketStatus: %s
                - brandCommTarget: %s
                - challenge: %s
                - cautions: %s
                """.formatted(
                brief.brandIntro(), brief.marketStatus(), brief.brandCommTarget(),
                brief.challenge(), brief.cautions()
        );

        String user = """
                %s
                Analyze the following submission and output a JSON object with:
                - "interpretation": exactly 2 sentences ([brief core] → [how reflected in campaign]).
                - "consistency": 3–4 sentences with concrete examples of alignment.
                """.formatted(briefBlock);

        return new OpenAiPrompt(system, user);
    }
}