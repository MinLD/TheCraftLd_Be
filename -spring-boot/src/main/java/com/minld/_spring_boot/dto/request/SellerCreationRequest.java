package com.minld._spring_boot.dto.request;

import com.minld._spring_boot.entity.MediaFile;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerCreationRequest {
   String name;

   String description;

   String phone;


   private MultipartFile image;

   private String taxCode; // Mã số thuế (nếu có)

   private LocalDate createdAt; // Ngày tạo công ty

   private LocalDate updatedAt;
}
