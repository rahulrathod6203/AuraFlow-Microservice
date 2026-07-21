package com.awp.userProfile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserProfileRequest(


        @NotBlank(message = "Name cannot be blank!")
        String name,

        @NotBlank(message = "Phone cannot be blank!")
        @Size(min = 10, message = "Enter a valid phone number!")
        String phone,

        @NotBlank(message = "Address cannot be blank!")
        @Size(min = 5, message = "Enter a valid address!!")
        String address

) {
}