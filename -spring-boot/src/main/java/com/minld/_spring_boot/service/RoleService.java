package com.minld._spring_boot.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minld._spring_boot.Repository.PermissionReponsitory;
import com.minld._spring_boot.Repository.RoleReponsitory;
import com.minld._spring_boot.dto.request.RoleRequest;
import com.minld._spring_boot.dto.response.RoleResponse;
import com.minld._spring_boot.mapper.RoleMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleReponsitory rolereponsitory;
    PermissionReponsitory permissionReponsitory;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionReponsitory.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = rolereponsitory.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        var role = rolereponsitory.findAll();
        return role.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String roleString) {
        rolereponsitory.deleteById(roleString);
    }
}
