package com.awp.userAuth.admin;

import com.fasterxml.jackson.annotation.JsonInclude;

public record UserResponse(

        Long id,

        String email,

        String password,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String role

) {
}
