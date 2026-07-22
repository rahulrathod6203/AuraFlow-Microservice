package com.awp.userAuth.mapper;

import com.awp.userAuth.admin.RegisteredUsers;
import com.awp.userAuth.dto.RegisterRequest;
import com.awp.userAuth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(RegisterRequest registerRequest);

    RegisteredUsers toResponse(User user);

}
