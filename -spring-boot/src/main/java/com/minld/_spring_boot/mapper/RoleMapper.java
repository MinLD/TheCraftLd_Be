package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.minld._spring_boot.dto.request.RoleRequest;
import com.minld._spring_boot.dto.response.RoleResponse;
import com.minld._spring_boot.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true) // bỏ qua field permissions
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
