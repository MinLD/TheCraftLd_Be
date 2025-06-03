package com.minld._spring_boot.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minld._spring_boot.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Set<Products> findBySellerIdAndCategoriesId(Long sellerId, Long categoriesId);
}
