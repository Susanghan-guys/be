package com.susanghan_guys.server.withdrawal.infrastructure.mapper;

import com.susanghan_guys.server.withdrawal.domain.Reason;
import com.susanghan_guys.server.withdrawal.domain.type.ReasonType;

import java.util.List;

public class ReasonMapper {

    public static Reason toEntity(String email, List<ReasonType> reasonTypes, String etc) {
        return Reason.builder()
                .email(email)
                .reasonTypes(reasonTypes)
                .etc(etc)
                .build();
    }
}
