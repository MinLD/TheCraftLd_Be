package com.minld._spring_boot.dto.request;

import com.minld._spring_boot.entity.Products_Order;
import jakarta.persistence.JoinColumn;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {

    private String status;

    private Long shippingAddress;

    private String paymentMethod;

    private Set<OrderItemCreationRequest> products_order;
}
