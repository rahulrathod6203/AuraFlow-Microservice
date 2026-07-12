package com.awp.userAuth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.Set;

@Builder
public record UserSummary(

        Long id,

        String email,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String role
)
{}