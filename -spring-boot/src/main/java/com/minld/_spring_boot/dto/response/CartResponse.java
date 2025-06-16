package com.minld._spring_boot.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minld._spring_boot.entity.Attributes;
import com.minld._spring_boot.entity.CartItem;
import com.minld._spring_boot.entity.MediaFile;
import com.minld._spring_boot.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private Long id;


    private Set<CartItemResponse> items ;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
