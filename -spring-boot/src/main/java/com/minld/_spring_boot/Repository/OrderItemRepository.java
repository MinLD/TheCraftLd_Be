package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.Address;
import com.minld._spring_boot.entity.Categories;
import com.minld._spring_boot.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
