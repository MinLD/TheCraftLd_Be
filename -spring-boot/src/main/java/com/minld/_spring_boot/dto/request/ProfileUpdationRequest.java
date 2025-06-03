package com.minld._spring_boot.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minld._spring_boot.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileUpdationRequest {

    @Size(min = 3, message = "NAME_INVALID")
    String fullName;

    String address;
    String city;
    String country;

    @Size(min = 10, message = "PHONE_INVALID")
    String phone;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;

    String gender;

    //     MultipartFile avatar;
}
