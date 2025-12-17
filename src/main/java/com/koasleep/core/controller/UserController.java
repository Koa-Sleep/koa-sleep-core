package com.koasleep.core.controller;

import com.koasleep.core.dto.ApiResponse;
import com.koasleep.core.dto.UserResponse;
import com.koasleep.core.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ApiResponse<UserResponse> findUserByEmail(@RequestParam String email) {
        UserResponse user = userService.getUserByEmail(email);
        return new ApiResponse<>("User retrieved successfully", user);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findUserById(@PathVariable UUID id) {
        UserResponse user = userService.getUserById(id);
        return new ApiResponse<>("User retrieved successfully", user);
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser(@AuthenticationPrincipal UUID userId) {
        UserResponse user = userService.getUserById(userId);
        return new ApiResponse<>("User retrieved successfully", user);
    }
}