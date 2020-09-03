package com.reddit.clone.controller;

import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/show")
    public String showPosts(Model model) {

        model.addAttribute("posts", postService.findAll());

        return "showposts";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getPostDto(Model model) {

        TextPostDto textPostDto = new TextPostDto();
        textPostDto.setContentType("text");

        model.addAttribute("post", textPostDto);

        return "createpost";
    }

    @PostMapping("/create")
    public String post(@ModelAttribute("post") TextPostDto textPostDto, Model model, @RequestParam("file") MultipartFile multipartFile) {

//        textPostDto.setContentType("text");
//
//        Post post = new Post(textPostDto.getTitle(), textPostDto.getContent(), textPostDto.getContentType());
//
//        postService.save(post);

        return "home";
    }
}
