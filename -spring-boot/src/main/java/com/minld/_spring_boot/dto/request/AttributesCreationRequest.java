package com.minld._spring_boot.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class AttributesCreationRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 120, message = "Title cannot exceed 120 characters")
    String name;
}
