package com.minld._spring_boot.dto.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerUpdationRequest {
    String name;

    String description;

    String phone;

    private MultipartFile image;

    private String taxCode; // Mã số thuế (nếu có)

    private LocalDate updatedAt;
}
