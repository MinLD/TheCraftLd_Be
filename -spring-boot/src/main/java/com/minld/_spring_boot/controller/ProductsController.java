package com.minld._spring_boot.controller;

import com.minld._spring_boot.dto.request.CategoriesCreationRequest;
import com.minld._spring_boot.dto.request.CategoriesUpdationRequest;
import com.minld._spring_boot.dto.request.ProductsCreationRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.CategoriesResponse;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.service.CategoriesService;
import com.minld._spring_boot.service.ProductsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductsController {
    ProductsService productsService;

    @PostMapping("/{id_categories}")
    ApiResponse<ProductsResponse> create(@PathVariable Long id_categories, @ModelAttribute ProductsCreationRequest request) {
        return ApiResponse.<ProductsResponse>builder().result(productsService.create(id_categories, request)).build();
    }

    @GetMapping()
    ApiResponse<List<ProductsResponse>> getAllProducts() {
        return ApiResponse.<List<ProductsResponse>>builder().result(productsService.getAllProducts()).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable Long id) {
        productsService.delete(id);
        return ApiResponse.<Void>builder()
                .result(productsService.delete(id))
                .build();
    }

    @GetMapping("/seller/{categoriesId}")
    public ApiResponse<List<ProductsResponse>>  getProductsBySellerAndCategory( @PathVariable Long categoriesId) {
        return ApiResponse.<List<ProductsResponse>>builder()
                .result(productsService.getProductsBySellerAndCategory(categoriesId))
                .build();
    }

}
