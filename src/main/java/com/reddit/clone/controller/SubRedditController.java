package com.reddit.clone.controller;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/subreddit")
public class SubRedditController {

    @Autowired
    SubredditService subredditService;

    @GetMapping("/create")
    public String subredditPage(){
        return "subreddit";
    }

    @PostMapping("/create")
    public String create(@Validated SubredditDto subredditDto){
        subredditService.save(subredditDto);
         return  "home";
    }
     @GetMapping("/get")
     @ResponseBody
    public List<Subreddit> getAllSubreddit(){
        return subredditService.findAll();
     }
}
