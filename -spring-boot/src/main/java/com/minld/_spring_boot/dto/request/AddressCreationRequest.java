package com.minld._spring_boot.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreationRequest {

    String name;


    String phone;


    String address;


    String detailsAddress;

    @JsonProperty("isDefault")
    boolean isDefault ;


    String isType;
}
