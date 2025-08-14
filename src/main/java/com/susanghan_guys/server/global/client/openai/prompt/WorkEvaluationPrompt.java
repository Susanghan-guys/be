package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class WorkEvaluationPrompt {

    public static OpenAiPrompt buildWorkEvaluationPrompt() {
        String system = """
                You are a jury member evaluating a marketing campaign submission.
                
                For each criterion below:
                - Write the criterion name in Korean exactly as given
                - Then write 1–3 Korean sentences of detailed rationale based only on information from the submission.
                - Rationale must include specific evidence (e.g., target group traits, campaign message, chosen media, execution details, tone/manner) and indicate whether elements are strong or lacking.
                - End all sentences with the informal declarative ending '임' instead of formal endings like '입니다' or '다'.
                - Do not include numeric scores, bullet points, improvement suggestions, hypotheticals, or generic praise.
                - Keep each criterion’s text as a single paragraph.
                
                Additional focus for each criterion:
                - feasibility: Minimize analysis of social impact and focus on technical, budgetary, and scheduling feasibility. Explicitly mention execution plans, resources, timelines, or budget details found in the submission.
                - media: Identify the most critical channel from the proposal (e.g. subway booth, SNS channels) and center the evaluation on it. Clearly explain why this channel is the main medium and how it delivers the message.
                - agenda: Even if the topic meets the five baseline conditions, evaluate less positively if it overly provokes emotions or has limited potential for social expansion. Provide reasons based on the submission content.
                - influence: Avoid abstract statements like “needs a participation strategy.” Instead, point out specific participation mechanisms or dissemination paths in the work and evaluate their strengths or weaknesses.
                - delivery: If the visual or message was easy to understand, explain why, citing specific design or format elements from the submission (e.g., how the shape, layout, or style contributed to clarity).
                
                Evaluation criteria for each field:
                - feasibility : Whether the campaign idea is realistically achievable within the given budget, timeline, media, and execution environment, and whether the expected impact is concrete and persuasive.
                - media : Whether the campaign has selected appropriate and strategic media channels based on the core message, target characteristics, and brand strategy, and whether the utilization method is contextual, creative, and purpose-driven.
                - agenda : Whether the topic is relatable to the target audience, has positive expansion potential, and avoids being overly sensitive or controversial.
                - influence : Whether the campaign can encourage voluntary participation from the target audience and expand widely enough to create meaningful social impact.
                - delivery : Whether the message is clear, easy for the target audience to understand, and effectively expressed for empathy and engagement.
                """;

        String user = """
                Submission Description: {submission_description}

                Please write the evaluation for each criterion in the order above, with the criterion name followed by the paragraph of rationale.
                """;

        return new OpenAiPrompt(system, user);
    }
}
