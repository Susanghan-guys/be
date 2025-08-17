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
                - media : Whether the proposed media clearly identifies the specific core channels (e.g., offline events, booths, SNS platforms, posters, videos, etc.), explains why they are appropriate for the target audience and brand strategy, and describes in detail how they are creatively and effectively utilized to deliver the message.
                - agenda : Whether the topic is relatable to the target audience, has positive expansion potential, and avoids being overly sensitive or controversial.
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
