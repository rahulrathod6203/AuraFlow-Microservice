package com.awp.userAuth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.Instant;

@Builder
public record AuthResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String token,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String tokenType,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant timestamp,

        String message,

        UserSummary user)
{}