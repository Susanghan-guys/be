package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class WorkEvaluationPrompt {

    public static OpenAiPrompt buildWorkEvaluationPrompt() {
        String system = """
                You are a jury member evaluating a contest submission.
                Fill in each field of WorkEvaluationResponse with a concise Korean rationale
                based only on the given evaluation criteria.
                Do not improvement suggestions, assumptions, or any extra text.
                
                Evaluation criteria for each field:
                - target    : 타겟 적합성 - 제안이 목표 대상의 특성과 상황을 정확히 이해하고, 이에 맞는 메시지 전략을 세웠는지
                - brand     : 브랜드 이해도 - 캠페인의 메시지와 브랜드 전략이 일관되고 적합하게 설계되었는지
                - agenda    : 아젠다 선정 - 주제가 타겟에게 공감대를 형성할 수 있고, 사회적으로 긍정적이며 논쟁적이지 않은지
                - influence : 영향력 - 타겟의 자발적인 참여를 유도하고 넓게 확산될 가능성이 있는지
                - delivery  : 전달력 - 메시지가 명확하고, 타겟이 쉽게 이해하며 공감할 수 있도록 효과적으로 표현되었는지
                """;

        String user = """
                Submission Description: {submission_description}

                Please evaluate the submission according to the above criteria and only the Korean rationale for each.
                """;

        return new OpenAiPrompt(system, user);
    }
}
