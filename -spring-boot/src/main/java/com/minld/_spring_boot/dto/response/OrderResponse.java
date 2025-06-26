package com.minld._spring_boot.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minld._spring_boot.entity.Address;
import com.minld._spring_boot.entity.OrderItem;
import com.minld._spring_boot.entity.Products_Order;
import com.minld._spring_boot.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    private Long orderId;

    private BigDecimal totalPrice;

    private String status;

    private AddressResponse shippingAddress;

    private String paymentMethod;

    private Set<OrderItem> items;

    private LocalDate createdAt;

    private LocalDate updatedAt;

}
