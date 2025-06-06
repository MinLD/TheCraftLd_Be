package com.minld._spring_boot.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendEmailRequest {
    @NotBlank(message = "EMAIL_NOT_BLANK")
    String toEmail;

    @NotBlank(message = "SUBJECT_NOT_BLANK")
    String subject;

    @NotBlank(message = "BODY_NOT_BLANK")
    String body;
}
