package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.Address;
import com.minld._spring_boot.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByName(String name);

    Optional<Categories> findByName(String name); // Sửa thành findByName
}