package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;
import com.susanghan_guys.server.personalwork.domain.type.DetailEvalType;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Collectors;

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

                Please write the evaluation for each criterion in the order above.
                """.formatted(briefBlock);

        return new OpenAiPrompt(system, user);
    }

    public static OpenAiPrompt buildDcaDetailEvaluationPrompt(
            DcaOpenAiRequest.BrandBriefPayload brief,
            EvaluationType type
    ) {

        boolean includeBrandOnly = Arrays.stream(DetailEvalType.values())
                .anyMatch(c -> c.getType() == type && c.name().equals("BRAND_ONLY_IDEA"));

        String brandOnlyBlock = includeBrandOnly ? """
                BRAND_ONLY_IDEA — stricter standards (APPLIES ONLY WHEN current sub-criterion == BRAND_ONLY_IDEA):
                - Do NOT treat general themes (e.g., fandom culture, portability, travel, graphics) as brand-exclusive.
                - Only credit brand-uniqueness if the idea is inseparably tied to the brand’s unique heritage, assets, rituals, or market context (e.g., brand-created holidays, specific home venues/regions, proprietary cultural assets).
                - If competitors in the same category could execute the concept with minor changes, classify it as generic and reduce the score.
                - Give high scores (8–9) ONLY when the execution clearly shows that no competitor could credibly own or deliver the idea.
                - Otherwise, stay conservative: typical cases should fall in the 6–7 range; polished but generic executions must not exceed 7.
                """ : "";

        String system = """
                You are a jury member evaluating a marketing campaign submission.

                For each sub-criterion:
                1. Output "rationale": 1-3 sentences in Korean, informal ending (~임, ~였음), one continuous paragraph.
                - Must directly reference at least one campaign concept, executional idea, or visual/strategic element.
                - **Do NOT copy or quote campaign slogans, taglines, or surface catchphrases; instead, paraphrase the underlying idea or describe its executional role.**
                    - No verbatim surface text: never quote titles/slogans/on-image copy; refer generically (e.g., "제시된 슬로건") if needed.
                    - Variety across criteria: do not reuse the same phrase across sub-criteria; use different evidence each time.
                - The rationale should be concise, concrete, and evaluative — not vague or repetitive.
                - When giving higher scores, justify with clear evidence linking the idea to the brand/target/context.
                - Avoid repeating the same slogan or surface phrase across multiple rationales; if mentioned once, rephrase or highlight a different aspect in other criteria.
                - **The description must always be written as a full sentence, including at least one explicit evidence or reasoning.**
                - **Do NOT output the criterion label itself as the description.**

                2. Output "score": INTEGER (0-10).

                Scoring rule:
                - Baseline: 4 points
                - 10-8 points: The work fully and clearly meets the criterion, going beyond expectations with concrete and persuasive execution. (e.g. a heavy theme reframed into a light participatory idea that feels fresh and positive)
                - 7-6 points: The work sufficiently meets the criterion but shows some lack of clarity, specificity, or persuasiveness. (e.g. a perception shift is attempted but remains limited or predictable)
                - 5 or below: The work fails to meet the core requirement of the criterion. (e.g. participation exists but the structure lacks potential to expand into a true public campaign)
                - If the score is 7 or below, and there is a clear reason for the lower score, add at least one limitation, weakness, or risk factor to justify it. \s
                - If no meaningful reason is apparent, you may omit this part instead of forcing a generic remark.

                Conservativeness:
                - Do NOT inflate scores without clear evidence.
                - Be conservative: most scores should be 8 or below.
                - Only give 9 or above if the rationale is highly specific, concrete, and convincingly tied to the campaign’s execution.
                - 10 should be almost impossible unless the rationale is exceptionally compelling and leaves virtually no room for improvement.
                
                BRAND BRIEF USAGE (conditional):
                - If EvaluationType == BRAND_UNDERSTANDING and a Brand Brief is provided, you may reference it ONLY to support brand-related rationale.
                - Use only identity/values/tone/positioning keywords from the brief.
                - Do NOT invent or introduce proper nouns not present in the submission.
                
                %s
                """.formatted(brandOnlyBlock);

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

            %s
            """.formatted(type.getType(), subCriteria, briefBlock);

        return new OpenAiPrompt(system, user);
    }
}