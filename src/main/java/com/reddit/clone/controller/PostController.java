package com.reddit.clone.controller;

import com.reddit.clone.configurations.metadata.AwsS3Credentials;
import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.dto.ResponsePostDto;
import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.*;
import com.reddit.clone.service.implementation.SubredditServiceImpl;
import com.reddit.clone.utility.TimestampToDays;
import org.springframework.beans.factory.annotation.Autowired;
import com.reddit.clone.service.FileService;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import com.reddit.clone.service.VoteService;
import com.reddit.clone.service.implementation.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.reddit.clone.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    SubredditRepository subredditRepository;

    private PostService postService;
    private FileService fileService;
    private AwsS3Credentials awsS3Credentials;
    private UserService userService;
    private VoteService voteService;
    private CommentService commentService;

    public PostController(PostService postService, FileService fileService, AwsS3Credentials awsS3Credentials, UserService userService, VoteService voteService, CommentService commentService) {
        this.postService = postService;
        this.fileService = fileService;
        this.awsS3Credentials = awsS3Credentials;
        this.userService = userService;
        this.voteService = voteService;
        this.commentService = commentService;
    }

    @GetMapping("/show")
    public String showPosts(Model model, Principal principal) {

        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);

        List<Post> posts = postService.findAll();

        if (principal != null) {
            User user = userService.findByUserName(principal.getName());

            model.addAttribute("posts", postService.getShowPostDtoList(posts, user));

            return "index";
        }


        model.addAttribute("posts", posts);


        return "index";
    }

    @GetMapping("/read")
    public String read(@RequestParam("id") String postId, Model model, Principal principal) {

        model.addAttribute("endpoint", awsS3Credentials.S3_BUCKET_NAME + "." + awsS3Credentials.S3_END_POINT);

        User user = userService.findByUserName(principal.getName());

        Post post = postService.findByPostId(Long.parseLong(postId));

        model.addAttribute("comments", commentService.findByPostId(postId));
        model.addAttribute("post", postService.getShowPostDto(post, user));

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
                       Principal principal) throws IOException, TwitterException {


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

        Twitter twitter = TwitterFactory.getSingleton();
        String message = post.getTitle()+"\n " +
                "http://mbreddit-clone.herokuapp.com/posts/read/?id="+post.getId();
        System.out.println(twitter.updateStatus(message).getText());
        return "redirect:/profile?sort?createdat";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePost(@RequestParam("postid") long postId, Model model, Principal principal) {

        Post post = postService.findByPostId(postId);
        User user = userService.findByUserName(principal.getName());
        Subreddit subreddit = subredditRepository.findBycommunityName(post.getSubreddit().getCommunityName());

        commentService.deleteByPost(post);
        subreddit.getPost().remove(post);
        subredditRepository.save(subreddit);

        userService.deleteSavedPosts(postId);
//        voteService.deleteAll(post.getVoteList());

        postService.delete(post);

        return "redirect:/all";


    }
}
