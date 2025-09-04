package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.StrengthWeaknessMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.DetailEvalRepository;
import com.susanghan_guys.server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StrengthWeaknessService {

    private final CurrentUserProvider currentUserProvider;
    private final PersonalWorkValidator personalWorkValidator;
    private final DetailEvalRepository detailEvalRepository;

    @Transactional(readOnly = true)
    public List<DetailEvaluationResponse.DetailEvaluation> getStrengths(Long workId) {
        User user = currentUserProvider.getCurrentUser();
        personalWorkValidator.validatePersonalWorkOwner(workId, user);
        personalWorkValidator.validateEvaluationExists(workId);

        List<DetailEval> strengths = detailEvalRepository.findTopStrengths(workId, PageRequest.of(0, 6));
        List<DetailEval> selected = adjustIfSameEvalTop3(strengths);

        return selected.stream().map(StrengthWeaknessMapper::toResponse).toList();

    }

    @Transactional(readOnly = true)
    public List<DetailEvaluationResponse.DetailEvaluation> getWeaknesses(Long workId) {
        User currentUser = currentUserProvider.getCurrentUser();
        personalWorkValidator.validatePersonalWorkOwner(workId, currentUser);
        personalWorkValidator.validateEvaluationExists(workId);

        List<DetailEval> weaknesses = detailEvalRepository.findBottomWeaknesses(workId, PageRequest.of(0, 6));
        List<DetailEval> selected = applyBoundaryThenReplaceIfSameEval(weaknesses);

        return selected.stream().map(StrengthWeaknessMapper::toResponse).toList();
    }


    private List<DetailEval> adjustIfSameEvalTop3(List<DetailEval> top6) {

        List<DetailEval> first3 = top6.subList(0, 3);
        Long e1 = first3.get(0).getEvaluation().getId();
        Long e2 = first3.get(1).getEvaluation().getId();
        Long e3 = first3.get(2).getEvaluation().getId();

        // 셋 다 같은 evaluation이면 교체
        if (e1.equals(e2) && e2.equals(e3)) {
            for (int i = 3; i < Math.min(6, top6.size()); i++) {
                DetailEval cand = top6.get(i);
                if (!cand.getEvaluation().getId().equals(e1)) {
                    List<DetailEval> adjusted = new java.util.ArrayList<>(first3);
                    adjusted.set(2, cand);
                    return adjusted;
                }
            }
            return first3;
        }
        return first3;
    }

    private List<DetailEval> applyBoundaryThenReplaceIfSameEval(List<DetailEval> bottom6) {

        // 경계 규칙 적용
        List<DetailEval> top3Slice = bottom6.subList(0, 3);
        List<DetailEval> base = applyBoundaryRule(top3Slice);

        if (base.size() < 3) return base;

        Long e1 = base.get(0).getEvaluation().getId();
        Long e2 = base.get(1).getEvaluation().getId();
        Long e3 = base.get(2).getEvaluation().getId();

        boolean allSameEval = e1.equals(e2) && e2.equals(e3);
        if (!allSameEval) return base;

        int s3 = base.get(2).getScore();

        for (int i = 3; i < Math.min(6, bottom6.size()); i++) {
            DetailEval cand = bottom6.get(i);
            if (cand.getScore() == s3 && !cand.getEvaluation().getId().equals(e1)) {
                List<DetailEval> adjusted = new java.util.ArrayList<>(base);
                adjusted.set(2, cand);
                return adjusted;
            }
        }

        return base.subList(0, 2);
    }

    private List<DetailEval> applyBoundaryRule(List<DetailEval> top3) {
        int s2 = top3.get(1).getScore();
        int s3 = top3.get(2).getScore();

        if (s2 != s3) return top3.subList(0, 2);

        return top3;
    }
}