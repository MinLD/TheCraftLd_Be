package com.minld._spring_boot.dto.response;

import java.util.Set;

import com.minld._spring_boot.entity.ProfileUser;
import com.minld._spring_boot.entity.Seller;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String email;
    String fullName;
    String code;
    Boolean isActive;
    ProfileUser profileUser;
    Set<RoleResponse> roles;
    Seller seller;
}
