package com.minld._spring_boot.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsUpdateRequest {

    String title;

    String description;

    Double price;

    Double discount;

    String trademark;

    String origin;

    String style;

    Double quantity;

    String material;

    List<MultipartFile> images;
}
