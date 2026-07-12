package com.awp.userProfile.service;

import com.awp.userProfile.dto.UserProfileRequest;
import com.awp.userProfile.dto.UserProfileResponse;
import com.awp.userProfile.dto.UserProfileResponsePage;
import com.awp.userProfile.entity.UserProfile;
import com.awp.userProfile.exception.userDomain.PhoneAlreadyExistsException;
import com.awp.userProfile.exception.userDomain.UserProfileNotFoundException;
import com.awp.userProfile.mapper.UserProfileMapper;
import com.awp.userProfile.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse getMyProfile(Long id) {
        log.info("Attempting to fetch details for UserProfile ID: {}", id);
        return userProfileRepository.findByUserAuthId(id)
                .map(user -> {
                    log.info("Successfully found profile details for UserProfile ID: {}", id);
                    return userProfileMapper.toResponse(user);
                })
                .orElseThrow(() -> {
                    log.warn("Fetch failed: UserProfile with the given ID : {} not found!", id);
                    return new UserProfileNotFoundException("Profile details not updated!, please update your profile.");
                });
    }

    @Override
    @Transactional
    public UserProfileResponse updateMyProfile(Long userAuthId, UserProfileRequest request) {

        UserProfile user = userProfileRepository
                .findByUserAuthId(userAuthId)
                .orElseGet(() -> {
                    UserProfile newUser = new UserProfile();
                    newUser.setUserAuthId(userAuthId);
                    log.info("Profile details not found!, updating profile...");
                    return newUser;
                });

        // Phone uniqueness check
        if (userProfileRepository.existsByPhoneAndUserAuthIdNot(
                request.phone(), userAuthId)) {
            throw new PhoneAlreadyExistsException("Phone number already exists.");
        }

        user.setName(request.name());
        user.setPhone(request.phone());
        user.setAddress(request.address());
        user.setActive(true);

        UserProfile savedUser = userProfileRepository.save(user);

        return userProfileMapper.toResponse(savedUser);
    }

    @Transactional
    @Override
    public String deleteMyProfile(Long id) {
        log.info("Attempting to delete user record from system for UserProfile ID: {}", id);

        UserProfile profile = userProfileRepository.findByUserAuthId(id).orElseThrow(() -> {
            log.warn("Delete failed: UserProfile with the given ID : {} not found!", id);
            return new UserProfileNotFoundException("Profile not found!, please update your profile.");
        });

        userProfileRepository.delete(profile);
        log.info("UserProfile with the given ID: {} successfully deleted from database.", id);

        return "Profile deleted successfully !";
    }
}
