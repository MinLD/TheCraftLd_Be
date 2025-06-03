package com.minld._spring_boot.dto.response;

import java.util.Set;

import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.MediaFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsResponse {
    Long id;

    String title;

    String description;

    Double price;

    Double discount;

    Long sku;

    String trademark;

    String origin;

    String style;

    Double quantity;

    String material;

    Set<MediaFile> images;

    Set<Attributes> attributes;

    Long id_Seller;

    //    Seller seller;

}
