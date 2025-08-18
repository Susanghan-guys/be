package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

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
}
