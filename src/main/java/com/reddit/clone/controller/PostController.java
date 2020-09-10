package com.reddit.clone.controller;

import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import com.reddit.clone.dto.ShowPostDto;

import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.*;
import com.reddit.clone.service.implementation.SubredditImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    SubredditRepository subredditRepository;



    @Autowired
    CommentService commentService;

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

        if (principal != null) {
            User user = userService.findByUserName(principal.getName());

                List<ShowPostDto> showPostDtoList = new ArrayList<>();
                for (Post post : posts) {
                    Vote vote = voteService.findByPostAndUser(post, user);
                    ShowPostDto showPostDto = new ShowPostDto(post);

                    if (vote != null) {
                        showPostDto.setIsVoted(true);
                        showPostDto.getIsUpVote(vote.isUpVote());
                    }
                    showPostDtoList.add(showPostDto);
                }

                model.addAttribute("posts", showPostDtoList);

                return "index";
        }


        model.addAttribute("posts", posts);


        return "index";
    }

    @GetMapping("/read")
    public String read(@RequestParam("id") String postId,Model model, Principal principal) {

        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);

            model.addAttribute("comments",commentService.findByPostId(postId));
            model.addAttribute("comment",  postService.findByPostId(Long.parseLong(postId)));
            return "comment";
    }



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getPostDto(Model model) {

        TextPostDto textPostDto = new TextPostDto();
        textPostDto.setContentType("text");

        model.addAttribute("post", textPostDto);

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

        Subreddit subreddit = subredditRepository.findBycommunityName(textPostDto.getSubredditName());
        post.setSubreddit(subreddit);

        postService.save(post);
        return "redirect:/home";
    }
}
