package com.minld._spring_boot.controller;

import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.MediaUpdateRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.MediaResponse;
import com.minld._spring_boot.service.MediaService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MediaController {
    MediaService mediaService;

    @PatchMapping("/{id}")
    ApiResponse<MediaResponse> update(@PathVariable Long id, @ModelAttribute MediaUpdateRequest request) {
        return ApiResponse.<MediaResponse>builder()
                .result(mediaService.updateFile(request, id))
                .build();
    }
}
