package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    PostService postService;
    UserService userService;

    public IndexController(PostService postService, UserService userService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public String getHotIndexPage(Model model, Principal principal) {

        if (principal != null) {
            User user = userService.findByUserName(principal.getName());

            List<Post> postList = postService.findBySubreddit(new ArrayList<>(user.getJoinedCommunitieList()));
            model.addAttribute("posts", postService.getShowPostDtoList(postList, user));

            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("hot");
        model.addAttribute("posts", postService.getShowPostDtoList(postList, null));
        return "index";
    }

    @GetMapping("/new")
    public String getNewPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("new");

        model.addAttribute("posts", postService.getShowPostDtoList(postList, null));
        return "index";
    }

    @GetMapping("/top")
    public String getTopPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("top");

        model.addAttribute("posts", postService.getShowPostDtoList(postList, null));
        return "index";
    }

    @GetMapping("/best")
    public String getRisingPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts("best");

        model.addAttribute("posts", postService.getShowPostDtoList(postList, null));
        return "index";
    }
}
