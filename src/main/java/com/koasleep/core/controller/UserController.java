package com.koasleep.core.controller;

import com.koasleep.core.dto.UserResponse;
import com.koasleep.core.model.User;
import com.koasleep.core.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public UserResponse findUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}