package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getHotIndexPage(Model model, Principal principal) {

        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("hot");

        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping("/new")
    public String getNewPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("new");

        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping("/top")
    public String getTopPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("top");

        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping("/best")
    public String getRisingPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("best");

        model.addAttribute("posts", postList);
        return "index";
    }
}
