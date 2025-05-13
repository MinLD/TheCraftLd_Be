package com.minld._spring_boot.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import com.minld._spring_boot.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Email(message = "EMAIL_INVALID")
    String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    @Size(min = 3, message = "NAME_INVALID")
    String fullName;

}
