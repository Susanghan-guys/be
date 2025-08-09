package com.susanghan_guys.server.work.dto.response;

import com.susanghan_guys.server.work.domain.Work;
import lombok.Builder;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Set;

@Builder
public record MyReportListResponse(
        List<MyReportResponse> responseList,
        Integer size,
        boolean hasNext,
        boolean isFirst,
        boolean isLast
) {
    public static MyReportListResponse of(Slice<Work> works, Set<Long> deletableWorks) {
        List<MyReportResponse> responseList = works.stream()
                .map(work -> MyReportResponse.of(work, deletableWorks.contains(work.getId())))
                .toList();

        return MyReportListResponse.builder()
                .responseList(responseList)
                .size(responseList.size())
                .hasNext(works.hasNext())
                .isFirst(works.isFirst())
                .isLast(works.isLast())
                .build();
    }
}
