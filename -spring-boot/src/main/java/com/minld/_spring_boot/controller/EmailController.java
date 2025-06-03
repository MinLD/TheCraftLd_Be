package com.minld._spring_boot.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minld._spring_boot.dto.request.SendEmailRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.service.EmailService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/email")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailController {
    EmailService emailService;

    @PostMapping
    public ApiResponse<Void> sendEmail(@Valid @RequestBody SendEmailRequest request) {
        emailService.sendEmailVerify("dodangminhluan@gmail.com", "123");
        return ApiResponse.<Void>builder().build();
    }
}
