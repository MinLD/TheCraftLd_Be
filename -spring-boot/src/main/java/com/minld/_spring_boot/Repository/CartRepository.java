package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
