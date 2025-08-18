package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;
import com.susanghan_guys.server.personalwork.domain.type.DetailEvalType;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WorkEvaluationPrompt {

    public static OpenAiPrompt buildWorkEvaluationPrompt() {
        String system = """
                You are a jury member evaluating a marketing campaign submission.
                
                For each criterion below:
                - Use the Korean criterion name exactly as given.
                - Write 1–3 sentences in Korean, based only on the submission.
                - Include concrete evidence (e.g., target traits, campaign message, chosen media, execution details, tone/manner).
                - End every sentence with an informal declarative ending (e.g., "~임", "~였음").
                - Do NOT mention or imply any scores, ratings, rankings, or numeric evaluations.

                Evaluation criteria:
                - feasibility : Whether the campaign idea is realistically achievable within the media, and execution environment, and whether the expected impact is concrete and persuasive.
                - media : Whether the campaign clearly presents the core media channels that directly deliver the message. Core media include not only conventional advertising (e.g. posters, videos, events) but also any tools (e.g. products, props, coupons) and spaces (e.g. cafes, booths, streets) used to convey the message. Assess why these media are appropriate for the target and brand strategy, and how they are creatively and effectively utilized. Do not emphasize additional diffusion channels; SNS or similar may be mentioned briefly if present, otherwise omitted.
                - agenda : Whether the topic is relatable to the target audience, broadly resonates with society, and has positive potential for expansion.
                - influence : Whether the campaign can encourage voluntary participation from the target audience and expand widely enough to create meaningful social impact.
                - delivery : Whether the message is clear, easy to understand, and emotionally engaging, and whether specific visual or expressive devices (e.g., particular phrases, slogans, imagery, or design elements) are identified and explained in detail to show how they enhance communication effectiveness.
                """;

        String user = """
                Submission Description: {submission_description}

                Please write the evaluation for each criterion in the order above, with the criterion name followed by the paragraph of rationale.
                """;

        return new OpenAiPrompt(system, user);
    }

    public static OpenAiPrompt buildYccDetailEvaluationPrompt(EvaluationType type) {
        String system = """
                You are a jury member evaluating a marketing campaign submission.
                
                Rules:
                - For each sub-criterion, produce TWO independent fields:
                1. "rationale": a critical, content-specific paragraph (Korean, 2–3 sentences).
                2. "score": an INTEGER on a 0–10 scale.
                
                - Independence: The rationale must NOT be softened to justify the score. Be candid and balanced (clear strengths AND weaknesses).

                - Style (rationale):
                1. Korean, informal declarative endings ("~임", "~였음").
                2. One continuous paragraph (no bullets, no line breaks inside).
                3. Explicitly mention at least one campaign concept, executional idea, or visual/strategic element observed in the submission images.
                4. DO NOT quote or copy the campaign title, slogans, or surface text directly from the submission.
                5. For AGENDA-related criteria:
                * Focus on how the campaign interprets the issue, extracts insights, and concretizes the problem.
                * Base reasoning on the campaign’s **core concept or strategy**, not superficial textual mentions.

                - Scoring guardrails (strict):
                * Default baseline: 4점 (not 5)
                * 0–2: clearly below standard / largely unmet
                * 3–5: partially met but lacking
                * 6–7: above average, sufficiently met
                * 8: well met (uncommon)
                * 9–10: exceptional and rare only
                * Do NOT give 8+ to most items. Be conservative by default.
                """;

        String subCriteria = Arrays.stream(DetailEvalType.values())
                .filter(c -> c.getType() == type)
                .map(c -> "- %s (%s): %s"
                        .formatted(c.name(), c.getLabel(), c.getPrompt()))
                .collect(Collectors.joining("\n"));

        String user = """
                WorkType: %s

                Evaluate the campaign description below according to the following sub-criteria.
                
                Sub-criteria:
                %s
                """.formatted(type.getType(), subCriteria);

        return new OpenAiPrompt(system, user);
    }
}
