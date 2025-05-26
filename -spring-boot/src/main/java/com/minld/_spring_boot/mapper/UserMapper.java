package com.minld._spring_boot.mapper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    User toAdminCreateUser(@Email(message = "EMAIL_INVALID") String email, @Size(min = 8, message = "PASSWORD_INVALID") String password, @Size(min = 3, message = "NAME_INVALID") String fullName);
}
