package com.awp.userProfile.mapper;

import com.awp.userProfile.dto.UserProfileRequest;
import com.awp.userProfile.dto.UserProfileResponse;
import com.awp.userProfile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    UserProfile toEntity(UserProfileRequest request);

    UserProfileResponse toResponse(UserProfile userProfile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateAppUser(UserProfileRequest request, @MappingTarget UserProfile userProfile);

}
