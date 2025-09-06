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
        personalWorkValidator.validatePersonalWorkAccessible(workId, currentUserProvider.getCurrentUser());
        personalWorkValidator.validateEvaluationExists(workId);

        List<DetailEval> strengths = detailEvalRepository.findTopStrengths(workId, PageRequest.of(0, 3));

        return strengths.stream().map(StrengthWeaknessMapper::toResponse).toList();

    }

    @Transactional(readOnly = true)
    public List<DetailEvaluationResponse.DetailEvaluation> getWeaknesses(Long workId) {
        personalWorkValidator.validatePersonalWorkAccessible(workId, currentUserProvider.getCurrentUser());
        personalWorkValidator.validateEvaluationExists(workId);

        List<DetailEval> weaknesses = detailEvalRepository.findBottomWeaknesses(workId, PageRequest.of(0, 3));
        List<DetailEval> bounded = applyBoundaryRule(weaknesses);

        return bounded.stream().map(StrengthWeaknessMapper::toResponse).toList();
}

    private List<DetailEval> applyBoundaryRule(List<DetailEval> sortedAscTop3) {
        int n = sortedAscTop3.size();
        if (n <= 2) return sortedAscTop3;

        int s2 = sortedAscTop3.get(1).getScore();
        int s3 = sortedAscTop3.get(2).getScore();

        if (s2 != s3) {
            return sortedAscTop3.subList(0, 2);
        }

        return sortedAscTop3;
    }
}