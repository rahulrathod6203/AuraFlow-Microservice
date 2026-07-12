package com.awp.userProfile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record UserProfileResponsePage(

        List<UserProfileResponse> content,

        int pageNo,

        int pageSize,

        int totalPages,

        boolean lastPage
) {
}