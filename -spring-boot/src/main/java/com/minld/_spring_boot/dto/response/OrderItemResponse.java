package com.minld._spring_boot.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minld._spring_boot.entity.Order;
import com.minld._spring_boot.entity.Products_Order;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {

    private Long id;

    private Products_Order products_order;

    private Integer quantity;

    private Double unitPrice;
}
