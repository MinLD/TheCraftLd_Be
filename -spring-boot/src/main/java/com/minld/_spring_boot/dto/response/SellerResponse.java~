package com.minld._spring_boot.dto.response;

import com.minld._spring_boot.entity.MediaFile;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerResponse {
   Long id;

   String name;

   String description;

   MediaFile image;

   private String taxCode; // Mã số thuế (nếu có)

   private LocalDate createdAt; // Ngày tạo công ty

   private LocalDate updatedAt;
}
