package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.minld._spring_boot.dto.request.UserCreationRequest;
import com.minld._spring_boot.dto.request.UserUpdationRequest;
import com.minld._spring_boot.dto.response.UserResponse;
import com.minld._spring_boot.entity.User;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user );

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdationRequest request);
}
