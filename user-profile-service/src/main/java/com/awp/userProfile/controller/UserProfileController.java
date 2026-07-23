package com.awp.userProfile.controller;

import com.awp.userProfile.clients.PeriodLogClient;
import com.awp.userProfile.config.UserPrincipal;
import com.awp.userProfile.dto.PeriodLogResponsePage;
import com.awp.userProfile.dto.UserProfileRequest;
import com.awp.userProfile.dto.UserProfileResponse;
import com.awp.userProfile.service.UserProfileService;
import com.awp.userProfile.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final PeriodLogClient periodLogClient;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMyProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userProfileService.getMyProfile(userPrincipal.getUserId()));
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileResponse> updateMyProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.updateMyProfile(userPrincipal.getUserId(), request));
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        String deletedMessage = userProfileService.deleteMyProfile(userPrincipal.getUserId());
        return ResponseEntity.ok().body(deletedMessage);
    }

    // ********************* Period Log of User // *********************

    @GetMapping("/logs")
    public PeriodLogResponsePage getAllPeriods(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NO) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
            ){

        return periodLogClient.getAllPeriodLogs(userPrincipal.getUserId(),pageNo,pageSize,sortBy).getBody();
    }
}