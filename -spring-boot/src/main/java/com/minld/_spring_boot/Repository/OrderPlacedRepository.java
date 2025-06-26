package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.Order;
import com.minld._spring_boot.entity.OrderPlaced;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPlacedRepository extends JpaRepository<OrderPlaced, Long> {

}
