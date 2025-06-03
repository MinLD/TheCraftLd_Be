package com.minld._spring_boot.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minld._spring_boot.entity.Address;
import com.minld._spring_boot.entity.Categories;

public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByName(String name);

    Optional<Categories> findByName(String name); // Sửa thành findByName
}
