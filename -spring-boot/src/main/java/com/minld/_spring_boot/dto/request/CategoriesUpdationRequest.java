package com.minld._spring_boot.dto.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoriesUpdationRequest {
    String name;

    String description;

    MultipartFile image;

    LocalDate updatedAt = LocalDate.now();
}
