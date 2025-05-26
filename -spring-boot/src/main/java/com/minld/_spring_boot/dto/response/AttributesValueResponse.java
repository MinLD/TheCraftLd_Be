package com.minld._spring_boot.dto.response;

import com.minld._spring_boot.entity.MediaFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesValueResponse {
    Long id;


    String name;



    Double price;

    MediaFile image;
}
