package com.reddit.clone.controller;


import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.repositoryImpl.PostRepositoryImpl;
import com.reddit.clone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
public class MainController {

    SubredditService subredditService;
    @Autowired
    PostRepositoryImpl getPostRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private AwsS3Credentials awsS3Credentials;
    @Autowired
    private UserService userService;

    public MainController(SubredditService subredditService) {
        this.subredditService = subredditService;
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search",required = false)String search, Model model, Principal principal){
        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);
        List<Post> posts= getPostRepository.fetchSearchedPost(search);

        System.out.println(posts);

        if (principal != null) {

            User user = userService.findByUserName(principal.getName());
            model.addAttribute("posts", postService.getShowPostDtoList(posts, user));
            model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

            return "profile";
        }

        List<ShowPostDto> showPostDtoList = postService.getShowPostDtoList(posts, null);

        System.out.println(showPostDtoList);

        model.addAttribute("posts", showPostDtoList );
        model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("subreddit", subredditService.findAll());
        return "main";
    }

    @RequestMapping(value = "/home")
    public String getHome() {
        return "home";
    }
}
