package com.minld._spring_boot.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoriesResponse {
    Long id;
    String name;
    String description;
    LocalDate createdAt;
    LocalDate updatedAt;
}
