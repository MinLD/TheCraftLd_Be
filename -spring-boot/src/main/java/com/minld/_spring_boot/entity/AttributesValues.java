package com.minld._spring_boot.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "quantity", nullable = false)
    Double quantity;

    @ManyToOne
    @JoinColumn(name = "attributes_id")
    @JsonBackReference
    Attributes attributes;

    @OneToOne
    @JoinColumn(name = "file_id", nullable = true)
    MediaFile image;
}
