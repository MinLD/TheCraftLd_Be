package com.minld._spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    String taxCode;

    @OneToOne
    @JoinColumn(name = "file_id")
    MediaFile image; // Ảnh logo công ty (Lưu trên Cloudinary)

    @OneToOne()
    @JsonBackReference
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false)
    private LocalDate createdAt; // Ngày tạo công ty

    @Column(nullable = false)
    private LocalDate updatedAt; // Ngày cập nhật gần nhất

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Products> products;

}

