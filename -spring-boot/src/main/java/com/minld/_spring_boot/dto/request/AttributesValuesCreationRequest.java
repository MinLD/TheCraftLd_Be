package com.minld._spring_boot.dto.request;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.MediaFile;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AttributesValuesCreationRequest {


    @NotBlank(message = "Name is required")
    @Size(max = 14, message = "Name cannot exceed 14 characters")
    String name;


    @NotNull(message = "Price is required")
    Double price;

    @NotNull(message = "Image is required")
    MultipartFile image;
}
