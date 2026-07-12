package com.awp.periodLog.dto;

import com.awp.periodLog.entity.FlowIntensity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;

@Builder
public record PeriodLogResponse(

        Long id,

        LocalDate startDate,

        LocalDate endDate,

        FlowIntensity flowIntensity,

        String notes,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant updatedAt

) {
}