package com.susanghan_guys.server.user.dto.request;

import com.susanghan_guys.server.user.domain.type.Channel;
import com.susanghan_guys.server.user.domain.type.Purpose;
import com.susanghan_guys.server.user.domain.type.Role;

import java.util.List;

public record UserOnboardingRequest(
        List<Role> role,
        Channel channel,
        String channelEtc,
        Purpose purpose,
        String purposeEtc
) {
}
