package com.minld._spring_boot.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.AddressCreationRequest;
import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.service.AddressService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {
    AddressService addressService;

    @PostMapping()
    public ApiResponse<AddressResponse> Create(@Valid @RequestBody AddressCreationRequest request) {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.Create(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<AddressResponse> update(
            @PathVariable Long id, @Valid @RequestBody AddressCreationRequest request) {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.update(id, request))
                .build();
    }
}
