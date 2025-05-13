package com.minld._spring_boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minld._spring_boot.entity.Permission;

@Repository
public interface PermissionReponsitory extends JpaRepository<Permission, String> {}
