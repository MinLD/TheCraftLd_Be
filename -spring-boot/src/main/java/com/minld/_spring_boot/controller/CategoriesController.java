package com.minld._spring_boot.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.service.CategoriesService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoriesController {
    CategoriesService categoriesService;

    @PostMapping()
    public ApiResponse<CategoriesResponse> create(@Valid @ModelAttribute CategoriesCreationRequest request) {
        return ApiResponse.<CategoriesResponse>builder()
                .result(categoriesService.create(request))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<CategoriesResponse>> getAll() {
        return ApiResponse.<List<CategoriesResponse>>builder()
                .result(categoriesService.GetAllCategories())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        categoriesService.deleteUser(id);
        return ApiResponse.<Void>builder().build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<CategoriesResponse> update(
            @PathVariable Long id, @ModelAttribute CategoriesUpdationRequest request) {
        return ApiResponse.<CategoriesResponse>builder()
                .result(categoriesService.update(id, request))
                .build();
    }

    @GetMapping("/products/{id}")
    public ApiResponse<List<ProductsResponse>> getProductsCategories(@PathVariable Long id) {
        return ApiResponse.<List<ProductsResponse>>builder()
                .result(categoriesService.getProductsCategories(id))
                .build();
    }
}
