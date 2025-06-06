package com.minld._spring_boot.dto.response;

import java.util.Set;

import com.minld._spring_boot.entity.MediaFile;
import com.minld._spring_boot.entity.Seller;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsCategoriesResponse {
    Long id;

    String title;

    String description;

    Double price;

    Double discount;

    Long sku;

    Set<MediaFile> images;

    Seller seller;
}
