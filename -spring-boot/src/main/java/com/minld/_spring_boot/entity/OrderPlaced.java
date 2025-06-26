package com.minld._spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderPlaced {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long id_order;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    Seller seller;
}
