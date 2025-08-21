package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class DcaWorkEvaluationPrompt {

    public static OpenAiPrompt buildDcaWorkEvaluationPrompt(DcaOpenAiRequest.BrandBriefPayload brief) {
        String system = """
                You are a jury member evaluating a marketing campaign submission.

                SOURCE PRIORITY (STRICT)
                1) Images (primary evidence) → 2) Submission text → 3) Brand Brief (brand only)
                - If images and brief/text conflict, FOLLOW IMAGES and ignore the brief/text.
                - Do NOT infer elements not visible in images or stated in submission.
                
                OUTPUT STYLE
                - Use the Korean criterion name exactly as given.
                - Write 2–3 sentences in Korean, based only on the submission.
                - Include concrete evidence (e.g., target traits, campaign message, chosen media, execution details, tone/manner).
                - End every sentence with an informal declarative ending (e.g., "~임", "~였음").
                - Do NOT mention or imply any scores, ratings, rankings, or numeric evaluations.
                
                BRAND BRIEF USAGE (optional)
                - If a Brand Brief is provided, use it ONLY for ‘brand’.
                - From the brief, you may use identity/values/tone/positioning words,
                  but DO NOT introduce proper nouns or examples not present in the submission.
                
                Evaluation criteria:
                - target : Whether the submission clearly defines the characteristics of the main target audience specified in the brief (needs, interests, behavioral patterns), and whether these are logically connected to the campaign idea.
                - brand : Whether the campaign idea and message reflect the brand’s unique identity (mission, values, communication tone, product/service attributes) and are logically consistent with the image and philosophy the brand has consistently delivered.
                - media : Whether the campaign clearly presents the core media channels that directly deliver the message. Core media include not only conventional advertising (e.g. posters, videos, events) but also any tools (e.g. products, props, coupons) and spaces (e.g. cafes, booths, streets) used to convey the message. Assess why these media are appropriate for the target and brand strategy, and how they are creatively and effectively utilized. Do not emphasize additional diffusion channels; SNS or similar may be mentioned briefly if present, otherwise omitted.
                - problem : Whether the campaign identifies and articulates the core problem not just by restating the brief, but by reinterpreting and specifying it in the context of the brand, target, and market environment.
                - feasibility : Whether the campaign idea is realistically achievable within the media, and execution environment, and whether the expected impact is concrete and persuasive.
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
                Submission Description: {submission_description}

                Please write the evaluation for each criterion in the order above.
                """.formatted(briefBlock);

        return new OpenAiPrompt(system, user);
    }
}