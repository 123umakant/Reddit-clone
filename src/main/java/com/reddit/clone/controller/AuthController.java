package com.reddit.clone.controller;

import com.reddit.clone.dto.RegisterDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class AuthController {


    @GetMapping
    public String sendSignUpPage(Model model) {
        model.addAttribute("user", new RegisterDto());
        return "signup";
    }

    @GetMapping
    public String register(@RequestBody RegisterDto registerDto) {

        return "";
    }

    @GetMapping("login")
    public String login() {

        return "";
    }

}
