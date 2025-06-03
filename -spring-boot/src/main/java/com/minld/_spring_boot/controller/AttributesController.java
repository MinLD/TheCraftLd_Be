package com.minld._spring_boot.controller;

import java.io.IOException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.AttributesCreationRequest;
import com.minld._spring_boot.dto.request.AttributesValuesCreationRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.AttributesResponse;
import com.minld._spring_boot.dto.response.AttributesValueResponse;
import com.minld._spring_boot.service.AttributesService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/attributes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttributesController {
    AttributesService attributesService;

    @PostMapping("/{id}")
    public ApiResponse<AttributesResponse> create(
            @PathVariable Long id, @Valid @RequestBody AttributesCreationRequest request) {
        return ApiResponse.<AttributesResponse>builder()
                .result(attributesService.CreateAttributes(id, request))
                .build();
    }

    @PostMapping("/value/{id}")
    public ApiResponse<AttributesValueResponse> createAtributesValue(
            @PathVariable Long id, @Valid @ModelAttribute AttributesValuesCreationRequest request) throws IOException {
        return ApiResponse.<AttributesValueResponse>builder()
                .result(attributesService.CreateAttributesValues(id, request))
                .build();
    }
}
