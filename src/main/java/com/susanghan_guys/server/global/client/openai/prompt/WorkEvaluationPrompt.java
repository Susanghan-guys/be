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
                - feasibility : 실현 가능성 - 캠페인 아이디어가 주어진 예산, 기간, 매체, 실행 환경 등 현실적인 제약을 고려하여 실행 가능한 수준으로 설계되었으며, 이를 통해 무엇이 바뀔 수 있는지에 대한 기대효과가 구체적이고 설득력 있게 제시되었는지
                - media : 매체 선정 - 캠페인의 핵심 메시지와 타겟 특성, 브랜드 전략을 바탕으로, 적절하고 전략적인 매체를 선정했으며, 그 활용 방식이 맥락에 맞고 창의적이며 목적 지향적으로 설계되었는지
                - agenda : 아젠다 선정 - 타겟이 쉽게 공감할 수 있는 긍정적인 확장 가능성을 지닌 주제이며, 사회적으로 민감하거나 과도하게 논쟁적이지 않은 주제를 선정했는지
                - influence : 영향력 - 캠페인이 타겟의 자발적인 참여를 유도하면서 넓은 범위로 확산되어서 유의미한 사회적 임팩트로 이어질 수 있는지
                - delivery : 전달력 - 메시지가 명확하고, 타겟이 쉽게 이해하며 공감할 수 있도록 효과적으로 표현되었는지
                """;

        String user = """
                Submission Description: {submission_description}

                Please evaluate the submission according to the above criteria and only the Korean rationale for each.
                """;

        return new OpenAiPrompt(system, user);
    }
}
