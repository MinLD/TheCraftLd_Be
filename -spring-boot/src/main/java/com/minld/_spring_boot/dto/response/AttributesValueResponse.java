package com.minld._spring_boot.dto.response;

import com.minld._spring_boot.entity.MediaFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesValueResponse {
    Long id;

    String name;

    Double price;

    Double quantity;

    MediaFile image;
}
