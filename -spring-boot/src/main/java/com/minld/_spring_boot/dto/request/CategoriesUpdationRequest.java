package com.minld._spring_boot.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

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
