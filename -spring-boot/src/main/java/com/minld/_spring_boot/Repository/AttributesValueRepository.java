package com.minld._spring_boot.Repository;


import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.AttributesValues;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttributesValueRepository extends JpaRepository<AttributesValues, Long> {

}
