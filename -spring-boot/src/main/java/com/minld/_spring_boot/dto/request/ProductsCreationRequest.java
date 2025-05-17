package com.minld._spring_boot.dto.request;


import com.minld._spring_boot.entity.MediaFile;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsCreationRequest {
    String title;

    String description;

    Double price;

    Double discount;

    List<MultipartFile> images;

}
