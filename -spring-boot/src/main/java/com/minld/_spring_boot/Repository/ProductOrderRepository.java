package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.CartItem;
import com.minld._spring_boot.entity.Products_Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<Products_Order, Long> {

}
