package com.susanghan_guys.server.global.client.openai.prompt;

import com.susanghan_guys.server.global.client.openai.DcaOpenAiRequest;
import com.susanghan_guys.server.global.client.openai.OpenAiPrompt;

public class DcaBriefEvaluationPrompt {
    public static OpenAiPrompt buildDcaBriefEvaluationPrompt(DcaOpenAiRequest.BrandBriefPayload brief) {
        String system = """
                You are a professional assistant that analyzes a competition entry into three fields:
                interpretation, consistency, weakness. Respond ONLY in Korean and return JSON with
                keys: interpretation, consistency, weakness.
                
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
                
                3) weakness
                - Exactly 1–2 sentences.\s
                - Write in a short, critical, and declarative style (e.g., "~이 부족하다", "~이 약하다", "~이 드러나지 않는다").
                - Always ground the weakness in a specific element of the submission (e.g., medium used, participation path, message framing).
                - Focus only on major misalignments with the brief’s core requirements or weak linkage to the brand’s essential value.
                - Do NOT include trivial design issues, technical usability, or time/place limitations.
                - Avoid phrasing like "~할 수 있다", "~기 때문이다"; always state issues directly and decisively.
                - If the work is overall strong, still provide one subtle risk framed in the same short, critical style.
                
                    # Good Weakness (use this style)
                    - "캐릭터 아이프렌즈로 귀여움과 친근함은 살렸으나, 브랜드와 직접적 연계가 약해 장기적 팬덤 구축까지는 한계가 있음."
                    - "버스 창문이 닫힐 때 전달되는 ‘채워짐’의 순간은 시각적으로 임팩트 있지만, 창문이 열리면 메시지가 단절되어 브랜드 기억 지속성이 약할 수 있다."
                
                    # Bad Weakness (do NOT write like this)
                    - "더 창의적이어야 한다."        # ambiguous
                    - "앱 사용성이 떨어질 수 있다."   # minor/Design Points
                    - "문제가 없다고 본다."         # uncritical
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
                - "weakness": **at least 1 sentence** (prefer 2), each naming a concrete element and its impact on brief/brand alignment; avoid design-only or time/place-specific remarks.
                Submission Description: {submission_description}
                """.formatted(briefBlock);

        return new OpenAiPrompt(system, user);
    }
}