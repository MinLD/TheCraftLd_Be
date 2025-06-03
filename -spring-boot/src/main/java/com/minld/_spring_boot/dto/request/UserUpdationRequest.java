package com.minld._spring_boot.dto.request;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdationRequest {
    @Size(min = 3, message = "NAME_INVALID")
    String fullName;

    @Email(message = "EMAIL_INVALID")
    String email;

    String password;
    List<String> roles;
}
