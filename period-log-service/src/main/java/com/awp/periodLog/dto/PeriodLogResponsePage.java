package com.awp.periodLog.dto;

import com.awp.periodLog.entity.PeriodLog;
import lombok.Builder;

import java.util.List;

@Builder
public record PeriodLogResponsePage(

        List<PeriodLogResponse> content,

        int pageNo,

        int pageSize,

        int totalPages,

        boolean lastPage
) {
}