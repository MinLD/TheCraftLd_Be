package com.minld._spring_boot.controller;

import java.util.List;

import com.minld._spring_boot.dto.request.*;
import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.minld._spring_boot.dto.response.ApiResponse;
import com.minld._spring_boot.dto.response.UserResponse;
import com.minld._spring_boot.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping()
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }
    @PostMapping("/admin")
    ApiResponse<UserResponse> createUserAdmin(@RequestBody @Valid AdminCreationUsersRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createAdmin(request));
        return apiResponse;
    }

    @PostMapping("/active")
    ApiResponse<String> ActiveUser(@RequestBody ActiveUserRequest request) {
        return ApiResponse.<String>builder()
                .result(userService.ActiveUser(request))
                .build();
    }

    @PostMapping("/sendcode")
    ApiResponse<String> sendCodeUser(@RequestBody @Valid SendCodeUserRequest request) {
        return ApiResponse.<String>builder()
                .result(userService.sendCodeUser(request.getEmail()))
                .build();
    }

    @GetMapping()
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        // Spring security tự map score cho nguoi dung
        log.info(authentication.getName());

        authentication.getAuthorities().forEach(auth -> log.info(auth.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "user had been deleted";
    }
}
