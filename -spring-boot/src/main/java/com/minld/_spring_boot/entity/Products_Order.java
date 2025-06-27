package com.minld._spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_product;

    private Long id_atributes_name;

    private String atributes_name;

    private Long id_seller;

    private String id_user;

    private Long id_atributes_value;

    private String atributes_Value;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "attributes_values_id")
//    private Set<AttributesValues> attributesValuesProduct;

    private Double price;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<MediaFile> images = new HashSet<>();
    //Nếu cascade = CascadeType.ALL hoặc orphanRemoval = true được bật trong Products_Order,
    // việc thay đổi images có thể dẫn đến xóa bản ghi MediaFile.
    // Nhưng vì MediaFile đang được AttributesValues tham chiếu, lỗi khóa ngoại xảy ra.

//Giải pháp:
//
//Xóa orphanRemoval = true hoặc giảm phạm vi cascade (ví dụ: chỉ dùng CascadeType.PERSIST và CascadeType.MERGE).
//Đảm bảo rằng MediaFile không bị xóa khi cập nhật products_order.




}
