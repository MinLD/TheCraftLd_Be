package com.minld._spring_boot.Repository;


import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface AttributesRepository extends JpaRepository<Attributes, Long> {

}
