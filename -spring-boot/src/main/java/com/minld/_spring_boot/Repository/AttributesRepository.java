package com.minld._spring_boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minld._spring_boot.entity.Attributes;

public interface AttributesRepository extends JpaRepository<Attributes, Long> {}
