package com.minld._spring_boot.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.entity.Products;
import com.minld._spring_boot.entity.Products_Order;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {

    private Long id;
    
    private Products_Order products_order;


    private Integer quantity;



    private Double unitPrice;

}
