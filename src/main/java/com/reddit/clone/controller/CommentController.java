package com.reddit.clone.controller;

import com.reddit.clone.dto.CommentDto;
import com.reddit.clone.service.implementation.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("/create")
    public String getCommentPage() {
        return "comment";
    }

    @PostMapping("/create")
    public String createComment(@Validated CommentDto commentDto, Model model) {
        System.out.println(commentDto);
        model.addAttribute("comment", commentService.save(commentDto));
        return "home";
    }

    @GetMapping("/read")
    public String readAllComments(Model model) {
        model.addAttribute("comment", commentService.findAll());
        return "comment";
    }
}
