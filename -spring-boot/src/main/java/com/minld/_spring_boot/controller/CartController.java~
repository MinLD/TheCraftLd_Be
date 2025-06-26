package com.minld._spring_boot.controller;

import com.minld._spring_boot.dto.request.CartCreationRequest;
import com.minld._spring_boot.dto.request.RoleRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.CartResponse;
import com.minld._spring_boot.dto.response.RoleResponse;
import com.minld._spring_boot.entity.Cart;
import com.minld._spring_boot.service.CartService;
import com.minld._spring_boot.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
   CartService cartService;

   @PostMapping()
   public ApiResponse<CartResponse> addItemToCart(@RequestBody CartCreationRequest request) {
       return ApiResponse.<CartResponse>builder().result(cartService.addItemToCart(request)).build();
   }
   @GetMapping()
   public ApiResponse<CartResponse> getCart() {
       return ApiResponse.<CartResponse>builder().result(cartService.getCart()).build();
   }
}
