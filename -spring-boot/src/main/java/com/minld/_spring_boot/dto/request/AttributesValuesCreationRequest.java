package com.minld._spring_boot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesValuesCreationRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 14, message = "Name cannot exceed 14 characters")
    String name;

    @NotNull(message = "Quantity is required")
    Double quantity;

    @NotNull(message = "Price is required")
    Double price;

    MultipartFile image;
}
