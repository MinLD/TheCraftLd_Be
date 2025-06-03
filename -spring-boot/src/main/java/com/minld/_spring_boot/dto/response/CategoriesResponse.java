package com.minld._spring_boot.dto.response;

import java.time.LocalDate;

import com.minld._spring_boot.entity.MediaFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    MediaFile image;
}
