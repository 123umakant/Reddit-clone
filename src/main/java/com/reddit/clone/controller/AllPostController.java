package com.reddit.clone.controller;

import com.amazonaws.services.ec2.model.transform.UserBucketStaxUnmarshaller;
import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import com.reddit.clone.service.VoteService;
import com.reddit.clone.utility.TimestampToDays;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/all")
public class AllPostController {

    PostService postService;
    UserService userService;
    VoteService voteService;

    public AllPostController(PostService postService, VoteService voteService, UserService userService) {
        this.voteService = voteService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public String getHotPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());
        List<ShowPostDto> showPostDtoList = postService.findSortedAllPosts("hot", user);
        model.addAttribute("posts", showPostDtoList);

        return "profile";
    }

    @GetMapping("/new")
    public String getNewPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<ShowPostDto> showPostDtoList = postService.findSortedAllPosts("new", user);

        model.addAttribute("posts", showPostDtoList);

        return "profile";
    }

    @GetMapping("/top")
    public String getTopPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<ShowPostDto> showPostDtoList = postService.findSortedAllPosts("top", user);

        model.addAttribute("posts", showPostDtoList);

        return "profile";
    }

    @GetMapping("/best")
    public String getRisingPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<ShowPostDto> showPostDtoList = postService.findSortedAllPosts("best", user);

        model.addAttribute("posts", showPostDtoList);

        return "profile";
    }
}
