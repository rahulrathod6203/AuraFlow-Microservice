package com.awp.userProfile.dto;


import com.awp.userProfile.entity.FlowIntensity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PeriodLogRequest(

        @NotNull(message = "Start date is required!")
        @PastOrPresent(message = "Start date cannot be in the future!")
        LocalDate startDate,

        @NotNull(message = "End date is required!")
        LocalDate endDate,

        @NotNull(message = "Flow intensity is required!")
        FlowIntensity flowIntensity,

        String notes

) {
}