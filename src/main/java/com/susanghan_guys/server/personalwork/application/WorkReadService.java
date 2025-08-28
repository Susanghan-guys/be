package com.susanghan_guys.server.personalwork.application;

import com.susanghan_guys.server.global.security.CurrentUserProvider;
import com.susanghan_guys.server.personalwork.application.validator.PersonalWorkValidator;
import com.susanghan_guys.server.personalwork.domain.DetailEval;
import com.susanghan_guys.server.personalwork.domain.type.EvaluationType;
import com.susanghan_guys.server.personalwork.dto.response.DetailEvaluationResponse;
import com.susanghan_guys.server.personalwork.exception.PersonalWorkException;
import com.susanghan_guys.server.personalwork.exception.code.PersonalWorkErrorCode;
import com.susanghan_guys.server.personalwork.infrastructure.mapper.DetailEvalMapper;
import com.susanghan_guys.server.personalwork.infrastructure.persistence.DetailEvalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkReadService {

    private final CurrentUserProvider currentUserProvider;
    private final PersonalWorkValidator personalWorkValidator;
    private final DetailEvalRepository detailEvalRepository;

    public DetailEvaluationResponse getDetailEvaluation(Long workId, EvaluationType type) {
        personalWorkValidator.validatePersonalWorkOwner(workId, currentUserProvider.getCurrentUser());
        personalWorkValidator.validateEvaluationExists(workId);

        List<DetailEval> detailEvals = detailEvalRepository.findByWorkIdAndEvaluationType(workId, type);

        if (detailEvals.isEmpty()) {
            throw new PersonalWorkException(PersonalWorkErrorCode.DETAIL_EVALUATION_TYPE_NOT_FOUND);
        }

        return DetailEvalMapper.toResponse(detailEvals);
    }
}