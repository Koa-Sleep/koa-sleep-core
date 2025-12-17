package com.koasleep.core.controller;

import com.koasleep.core.config.AppProperties;
import com.koasleep.core.dto.UserResponse;
import com.koasleep.core.service.JwtService;
import com.koasleep.core.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AppProperties appProperties;


    public DemoController(UserService userService, JwtService jwtService, AppProperties appProperties) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.appProperties = appProperties;
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        UserResponse user = userService.getUserByEmail("demo@koa");
        String token = jwtService.generateToken(user.getId());

        Cookie cookie = new Cookie("auth-token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) (jwtService.getExpirationTime() / 1000));
        // cookie.setSecure(true); // Uncomment when running on HTTPS/Production

        response.addCookie(cookie);
        response.sendRedirect(appProperties.getFrontendUrl() + "/home");
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("auth-token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        response.sendRedirect(appProperties.getFrontendUrl() + "/");
    }
}