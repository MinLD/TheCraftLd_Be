package com.minld._spring_boot.mapper;

import org.mapstruct.Mapper;

import com.minld._spring_boot.dto.request.PermissionRequest;
import com.minld._spring_boot.dto.response.PermissionResponse;
import com.minld._spring_boot.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
