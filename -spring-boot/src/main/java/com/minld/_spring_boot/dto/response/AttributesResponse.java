package com.minld._spring_boot.dto.response;

import com.minld._spring_boot.dto.request.AttributesValuesCreationRequest;
import com.minld._spring_boot.entity.MediaFile;
import com.minld._spring_boot.entity.Seller;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesResponse {
    Long id;

    String name;
}
