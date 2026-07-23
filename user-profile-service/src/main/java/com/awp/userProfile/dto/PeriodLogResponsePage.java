package com.awp.userProfile.dto;

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