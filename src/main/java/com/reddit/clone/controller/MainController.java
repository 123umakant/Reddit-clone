package com.reddit.clone.controller;


import com.reddit.clone.configurations.metadata.AwsS3Credentials;
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
    private PostService postService;
    private FileService fileService;
    private AwsS3Credentials awsS3Credentials;
    private UserService userService;
    private VoteService voteService;
    private CommentService commentService;

    public MainController(SubredditService subredditService) {
        this.subredditService = subredditService;
    }
//    @RequestMapping(value = "/")
//    public String getIndex(Principal principal){
//
//        if(principal != null){
//            return "profile";
//        }
//        return "redirect:/posts/show";
//    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search",required = false)String search, Model model, Principal principal){
        System.out.println(search);
        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);
        List<Post> posts= getPostRepository.fetchSearchedPost(search);

      /*  if (principal != null) {

            User user = userService.findByUserName(principal.getName());
            System.out.println(user);
            model.addAttribute("posts", postService.getShowPostDtoList(posts, user));

            return "search";
        }*/


        model.addAttribute("posts", posts);

    return "search";
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
