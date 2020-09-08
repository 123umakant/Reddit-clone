package com.reddit.clone.controller;

import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.dto.ResponsePostDto;
import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.service.FileService;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import com.reddit.clone.service.VoteService;
import com.reddit.clone.service.implementation.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private FileService fileService;
    private AwsS3Credentials awsS3Credentials;
    private UserService userService;
    private VoteService voteService;

    public PostController(PostService postService, FileService fileService, AwsS3Credentials awsS3Credentials, UserService userService, VoteService voteService) {
        this.postService = postService;
        this.fileService = fileService;
        this.awsS3Credentials = awsS3Credentials;
        this.userService = userService;
        this.voteService = voteService;
    }

    @GetMapping("/show")
    public String showPosts(Model model, Principal principal) {

        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);


        List<Post> posts = postService.findAll();

        model.addAttribute("posts", posts);

        return "index";
    }



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getPostDto(Model model) {

        TextPostDto textPostDto = new TextPostDto();
        textPostDto.setContentType("text");

        model.addAttribute("post", textPostDto);
        model.addAttribute("pagetype", "createpost");
        return "createpost";
    }

    @PostMapping("/create")
    public String post(@ModelAttribute("post") TextPostDto textPostDto, Model
            model, @RequestParam(value = "file", required = false) MultipartFile multipartFile,
                       Principal principal) throws IOException {

        if (textPostDto.getContentType().equals("media")) {
            String fileName = fileService.upLoadFile(multipartFile);

            String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);

            if (extension.equals("mp4")) {
                textPostDto.setContentType("video");
            } else {
                textPostDto.setContentType("image");
            }

            textPostDto.setContent(fileName);
        }

        Post post = new Post(textPostDto.getTitle(), textPostDto.getContent(), textPostDto.getContentType());
        User loggedUser = userService.findByUserName(principal.getName());

        loggedUser.getPostList().add(post);
        post.setUser(loggedUser);


        postService.save(post, textPostDto);


        return "redirect:/profile?sort?createdat";
    }
}
