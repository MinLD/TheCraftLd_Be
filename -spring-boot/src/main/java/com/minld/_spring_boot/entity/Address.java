package com.minld._spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "details_address", nullable = false)
    String detailsAddress;

    @Column(name = "is_Default")
    boolean isDefault = false;

    @Column(name = "is_type")
    String isType ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    @JsonBackReference
    Seller seller;


}
