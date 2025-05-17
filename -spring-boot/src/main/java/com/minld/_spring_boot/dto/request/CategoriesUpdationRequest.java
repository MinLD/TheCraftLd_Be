package com.minld._spring_boot.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoriesUpdationRequest {
    String name;

    String description;


    LocalDate updatedAt = LocalDate.now();
}
