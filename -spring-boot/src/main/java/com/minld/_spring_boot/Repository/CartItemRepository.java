package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
