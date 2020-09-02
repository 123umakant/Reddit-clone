package com.reddit.clone.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class CommonController {

        @GetMapping(value = "")
        public String home(){
            return "home";
        }
}
