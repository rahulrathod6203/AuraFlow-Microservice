package com.awp.userAuth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginRequest(

        @NotBlank(message = "Email cannot be blank!")
        @Email(message = "Enter a valid email!")
        String email,

        @NotBlank(message = "Password cannot be blank!")
        @Size(min = 8, message = "Invalid password!")
        String password
) {
}