package com.minld._spring_boot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

//    @JsonProperty("isDefault")
    boolean AddressDefault;

    String isType;
}
