package com.reddit.clone.controller;


import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@Controller
public class MainController {


    @Autowired
    SubredditService subredditService;

    @RequestMapping(value = "/")
    public String getIndex(Principal principal){

        if(principal != null){
            return "profile";
        }
        return "redirect:/posts/show";
    }

    @GetMapping("/main")
    public String main(Model model){
        System.out.println(subredditService.findAll().get(0).getPost().get(0).getTitle());
        model.addAttribute("subreddit",subredditService.findAll());
    return "main";
    }

    @RequestMapping(value = "/home")
    public String getHome(){
        return "home";
    }
}
