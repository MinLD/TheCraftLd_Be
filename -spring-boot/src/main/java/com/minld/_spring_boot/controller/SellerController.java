package com.minld._spring_boot.controller;

import java.io.IOException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.SellerCreationRequest;
import com.minld._spring_boot.dto.request.SellerUpdationRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.SellerResponse;
import com.minld._spring_boot.service.SellerService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellerController {
    SellerService sellerService;

    @PostMapping()
    ApiResponse<SellerResponse> create(@Valid @ModelAttribute SellerCreationRequest request) throws IOException {
        return ApiResponse.<SellerResponse>builder()
                .result(sellerService.create(request))
                .build();
    }

    @PatchMapping("/{id}")
    ApiResponse<SellerResponse> update(@PathVariable Long id, @ModelAttribute SellerUpdationRequest request)
            throws IOException {
        return ApiResponse.<SellerResponse>builder()
                .result(sellerService.update(id, request))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<SellerResponse> get(@PathVariable Long id) {
        return ApiResponse.<SellerResponse>builder()
                .result(sellerService.get(id))
                .build();
    }
}
