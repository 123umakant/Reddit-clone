package com.reddit.clone.controller;

import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.service.FileService;
import com.reddit.clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private FileService fileService;
    private AwsS3Credentials awsS3Credentials;

    public PostController(PostService postService, FileService fileService, AwsS3Credentials awsS3Credentials) {
        this.postService = postService;
        this.fileService = fileService;
        this.awsS3Credentials = awsS3Credentials;
    }

    @GetMapping("/show")
    public String showPosts(Model model) {

        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);

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
    public String post(@ModelAttribute("post") TextPostDto textPostDto, Model model, @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {

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

        postService.save(post);

        return "redirect:/posts/create";
    }
}
