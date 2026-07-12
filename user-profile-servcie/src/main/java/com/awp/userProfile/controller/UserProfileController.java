package com.awp.userProfile.controller;

import com.awp.userProfile.config.UserPrincipal;
import com.awp.userProfile.dto.UserProfileRequest;
import com.awp.userProfile.dto.UserProfileResponse;
import com.awp.userProfile.dto.UserProfileResponsePage;
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
}