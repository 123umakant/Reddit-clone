package com.reddit.clone.controller;

import com.reddit.clone.dto.RegisterDto;
import com.reddit.clone.model.User;
import com.reddit.clone.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String sendSignUpPage(Model model) {
        model.addAttribute("user", new RegisterDto());
        return "signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterDto registerDto) {
        User user = new User(registerDto.getName(), registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword()));
        userService.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
