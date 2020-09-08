package com.reddit.clone.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@Controller
@SessionAttributes({"page","sort"})
public class MainController {

    @RequestMapping(value = "/")
    public String getIndex(Principal principal, Model model, HttpServletRequest request){

        if(principal != null){
            return "profile";
        }
        return "redirect:/posts/show";
    }


    @RequestMapping(value = "/home")
    public String getHome(){
        return "home";
    }
}
