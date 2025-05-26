package com.minld._spring_boot.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name" , nullable = false)
    String name;


    @Column(name = "description" , nullable = true)
    String description;

    @OneToOne
    @JoinColumn(name = "file_id" , nullable = true)
    MediaFile image;

     LocalDate createdAt;
     LocalDate updatedAt;

     @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
     Set<Products> products;


}
