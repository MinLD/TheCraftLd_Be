package com.minld._spring_boot.controller;

import com.minld._spring_boot.dto.request.AddressCreationRequest;
import com.minld._spring_boot.dto.request.OrderCreationRequest;
import com.minld._spring_boot.dto.response.AddressResponse;
import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.OrderResponse;
import com.minld._spring_boot.service.AddressService;
import com.minld._spring_boot.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping
    public ApiResponse<List<OrderResponse>> createOrder(@RequestBody OrderCreationRequest request) {

        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.createOrder(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Set<OrderResponse>> getOrder() {
        return ApiResponse.<Set<OrderResponse>>builder()
                .result(orderService.getOrder())
                .build();
    }
}
