package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;
import com.susanghan_guys.server.personalwork.domain.type.DetailEvalType;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class YccWorkEvaluationPrompt {

    public static OpenAiPrompt buildYccWorkEvaluationPrompt() {
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

                For each sub-criterion:
                1. Output "rationale": 1-3 sentences in Korean, informal ending (~임, ~였음), one continuous paragraph.
                - Must directly reference at least one campaign concept, executional idea, or visual/strategic element from the submission.
                - Do NOT copy the campaign title, slogans, or surface text.
                - The rationale should be short, concrete, and sound like an actual evaluation comment (similar in style to professional jury scoring), not vague feedback.
                2. Output "score": INTEGER (0-10).

                Scoring rule:
                - Baseline: 4 points
                - 10-8 points: The work fully and clearly meets the criterion, going beyond expectations with concrete and persuasive execution. (e.g. a heavy theme reframed into a light participatory idea that feels fresh and positive)
                - 7-6 points: The work sufficiently meets the criterion but shows some lack of clarity, specificity, or persuasiveness. (e.g. a perception shift is attempted but remains limited or predictable)
                - 5 or below: The work fails to meet the core requirement of the criterion. (e.g. participation exists but the structure lacks potential to expand into a true public campaign)

                Conservativeness:
                - Be conservative: most scores should be 8 or below.
                - Only give 9 or above if the rationale is highly specific, concrete, and convincingly tied to the campaign’s execution.
                - 10 should be almost impossible unless the rationale is exceptionally compelling and leaves virtually no room for improvement.
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
