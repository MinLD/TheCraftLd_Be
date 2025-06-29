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
public class CartCreationRequest {

    Long id_product;

    Integer quantity;


    Long id_atributes_value ;

    Long id_atributes_name;
}
