package com.susanghan_guys.server.withdrawal.application;

import com.susanghan_guys.server.withdrawal.domain.Reason;
import com.susanghan_guys.server.withdrawal.domain.type.ReasonType;
import com.susanghan_guys.server.withdrawal.infrastructure.mapper.ReasonMapper;
import com.susanghan_guys.server.withdrawal.infrastructure.persistence.ReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReasonService {

    private final ReasonRepository reasonRepository;

    @Transactional
    public void saveWithdrawalReason(String name, List<ReasonType> reasonTypes, String etc) {
        Reason reason = ReasonMapper.toEntity(name, reasonTypes, etc);
        reasonRepository.save(reason);
    }
}
