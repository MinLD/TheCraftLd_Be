package com.minld._spring_boot.dto.request;

import jakarta.validation.constraints.Email;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendCodeUserRequest {

    @Email(message = "EMAIL_INVALID")
    String email;
}
