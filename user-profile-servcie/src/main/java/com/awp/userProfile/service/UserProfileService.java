package com.awp.userProfile.service;

import com.awp.userProfile.dto.UserProfileRequest;
import com.awp.userProfile.dto.UserProfileResponse;
import com.awp.userProfile.dto.UserProfileResponsePage;

public interface UserProfileService {


    UserProfileResponse getMyProfile(Long id);

    UserProfileResponse updateMyProfile(Long id, UserProfileRequest request);

    String deleteMyProfile(Long id);
}
