package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/posts/{postid}")
public class SavePostController {

    private PostService postService;
    private UserService userService;

    public SavePostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping("/save")
    public String savePost(@PathVariable("postid") long postId, Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        Post post = postService.findByPostId(postId);

        user.getSavedPostList().add(post);

        userService.save(user);

        return "redirect:/all";
    }

    @RequestMapping("/unsave")
    public String unSavePost(@PathVariable("postid") long postId, Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        Post post = postService.findByPostId(postId);

        user.getSavedPostList().remove(post);

        userService.save(user);

        return "redirect:/all";
    }


}
