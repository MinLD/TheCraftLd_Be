package com.minld._spring_boot.controller;

import com.minld._spring_boot.dto.request.CartCreationRequest;
import com.minld._spring_boot.dto.request.RoleRequest;
import com.minld._spring_boot.dto.request.UpdationCartItemRequest;
import com.minld._spring_boot.dto.request.UpdationQuantityCartRequest;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.AttributesValueResponse;
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
import java.util.Set;

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

   @DeleteMapping("/{id}")
   public ApiResponse<Void> deleteCartItem(@PathVariable Long id) {
       cartService.deleteCartItem(id);
       return ApiResponse.<Void>builder().build();
   }

   @PostMapping("/updateQuantity")
   public ApiResponse<Void> UpdateQuantity(@RequestBody UpdationQuantityCartRequest request) {
       return ApiResponse.<Void>builder().result(cartService.UpdateQuantity(request.getId(), request.getQuantity())).build();
   }
   @PostMapping("/updateCartItem")
   public ApiResponse<Void> UpdateCartItem(@RequestBody UpdationCartItemRequest request) {
       return ApiResponse.<Void>builder().result(cartService.UpdateCartItem(request.getId(), request.getId_atributes_value())).build();
   }

   @GetMapping("/AtributesValue/{id}")
   public ApiResponse<Set<AttributesValueResponse>> getAtributesValue(@PathVariable Long id) {
       return ApiResponse.<Set<AttributesValueResponse>>builder().result(cartService.getAttributes(id)).build();
   }
}
