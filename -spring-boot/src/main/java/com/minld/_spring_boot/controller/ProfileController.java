package com.minld._spring_boot.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.ProfileUpdationRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.ProfileResonse;
import com.minld._spring_boot.service.ProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    ProfileService profileService;

    @PatchMapping("/{id}")
    public ApiResponse<ProfileResonse> updateProfile(@PathVariable Long id, @RequestBody ProfileUpdationRequest request)
            throws IOException {

        return ApiResponse.<ProfileResonse>builder()
                .result(profileService.update(id, request))
                .build();
    }
}
