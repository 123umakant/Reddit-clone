package com.reddit.clone.controller;

import com.reddit.clone.dto.RegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto){

        return "";
    }

    @GetMapping("login")
    public String login(){

        return "";
    }

}
