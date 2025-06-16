package com.minld._spring_boot.controller;

import java.util.List;
import java.util.Map;

import com.minld._spring_boot.dto.response.CategoriesResponse;
import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.request.ProductsCreationRequest;
import com.minld._spring_boot.dto.request.ProductsUpdateRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.ProductsResponse;
import com.minld._spring_boot.service.ProductsService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductsController {
    ProductsService productsService;

    @PostMapping("/{id_categories}")
    ApiResponse<ProductsResponse> create(
            @PathVariable Long id_categories, @ModelAttribute ProductsCreationRequest request) {
        return ApiResponse.<ProductsResponse>builder()
                .result(productsService.create(id_categories, request))
                .build();
    }

    @GetMapping("/myseller/{id_Seller}")
    ApiResponse<List<ProductsResponse>> getAllProducts(@PathVariable Long id_Seller) {
        return ApiResponse.<List<ProductsResponse>>builder()
                .result(productsService.getAllProducts(id_Seller))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable Long id) {
        productsService.delete(id);
        return ApiResponse.<Void>builder().result(productsService.delete(id)).build();
    }

    @GetMapping("/{id_Seller}/{categoriesId}")
    public ApiResponse<List<ProductsResponse>> getProductsBySellerAndCategory(@PathVariable Long id_Seller,@PathVariable Long categoriesId) {
        return ApiResponse.<List<ProductsResponse>>builder()
                .result(productsService.getProductsBySellerAndCategory(id_Seller,categoriesId))
                .build();
    }

    @PatchMapping("/{id}")
    ApiResponse<ProductsResponse> update(@PathVariable Long id, @ModelAttribute ProductsUpdateRequest request) {
        return ApiResponse.<ProductsResponse>builder()
                .result(productsService.update(id, request))
                .build();
    }
    @GetMapping("/by/{id}")
    public ApiResponse<ProductsResponse> getProductsById(@PathVariable Long id) {
        return ApiResponse.<ProductsResponse>builder()
                .result(productsService.getProductsById(id))
                .build();
    }

    @GetMapping("/topview")
    public ApiResponse <List<ProductsResponse>> getTopViewProductsByCategory() {
        return ApiResponse. <List<ProductsResponse>>builder()
                .result(productsService.getTopViewProductsByCategory())
                .build();
    }
}
