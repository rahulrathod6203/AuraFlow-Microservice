package com.awp.userProfile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record UserProfileResponse(

        Long id,

        String name,

        String phone,

        String address,

        Boolean active,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant updatedAt


) {
}