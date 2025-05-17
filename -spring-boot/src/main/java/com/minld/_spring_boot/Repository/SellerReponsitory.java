package com.minld._spring_boot.Repository;

import com.minld._spring_boot.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerReponsitory extends JpaRepository<Seller, Long> {
}
