package com.reddit.clone.controller;

import com.reddit.clone.dto.CommentDto;
import com.reddit.clone.model.User;
import com.reddit.clone.service.UserService;
import com.reddit.clone.service.implementation.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    UserService userService;

    @GetMapping("/create")
    public String getCommentPage() {
        return "comment";
    }

    @PostMapping("/create")
    public String createComment(@Validated CommentDto commentDto, Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("comment", commentService.save(commentDto, user));
        return "redirect:/posts/read/?id=" + commentDto.getPostId();
    }

    @GetMapping("/read")
    public String readAllComments(Model model) {
        model.addAttribute("comment", commentService.findAll());
        return "comment";
    }
}
