package com.reddit.clone.controller;

import com.reddit.clone.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @PostMapping("/create")
    public void post(@RequestBody PostDto postDto) {


    }
}
