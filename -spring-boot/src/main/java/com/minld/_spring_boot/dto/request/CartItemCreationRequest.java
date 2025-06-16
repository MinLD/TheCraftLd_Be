package com.minld._spring_boot.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemCreationRequest {

    String title;

    @Size(min = 20, message = "DESCRIPTION_INVALID")
    String description;

    @Size(min = 1, message = "PRICE_INVALID")
    Double price;

    @Size(min = 1, message = "DISCOUNT_INVALID")
    Double discount;

    @Size(min = 3, message = "TRADEMARK_INVALID")
    String trademark;

    @Size(min = 3, message = "ORIGIN_INVALID")
    String origin;

    @Size(min = 3, message = "STYLE_INVALID")
    String style;

    @Size(min = 1, message = "QUANTITY_INVALID")
    Double quantity;

    @Size(min = 3, message = "MATERIAL_INVALID")
    String material;

    List<MultipartFile> images;
}
