package com.reddit.clone.controller;

import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.service.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private PostService postService;
    private UserService userService;
    private VoteService voteService;
    private CommentService commentService;
    private SubredditService subredditService;

    public ProfileController(PostService postService, UserService userService, VoteService voteService, CommentService commentService, SubredditService subredditService) {
        this.postService = postService;
        this.userService = userService;
        this.voteService = voteService;
        this.commentService = commentService;
        this.subredditService = subredditService;
    }

    @GetMapping
    public String goToProfile(@RequestParam(value = "sort", required = false) String sort, Principal principal,
                              @ModelAttribute("page") String page, HttpServletRequest request,
                              Model model) {
        User user = userService.findByUserName(principal.getName());

        List<Post> postList = postService.findByUser(user, Sort.by(Sort.Direction.DESC, "createdAt"));

        model.addAttribute("posts", postService.getShowPostDtoList(postList, user));
        model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

        return "profile";
    }

    @RequestMapping("/comments")
    public String getUserComments(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<Comment> commentList = commentService.findByUser(user);

        model.addAttribute("comments", commentList);
        model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

        return "profile";
    }

    @RequestMapping("/upvoted")
    public String getUpVotedPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<Post> upVotedPostList = voteService.findUpVotesByUser(user);

        model.addAttribute("posts", postService.getShowPostDtoList(upVotedPostList, user));
        model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

        return "profile";
    }

    @RequestMapping("/downvoted")
    public String getDownVotedPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<Post> downVotedPostList = voteService.findDownVotesByUser(user);

        model.addAttribute("posts", postService.getShowPostDtoList(downVotedPostList, user));
        model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

        return "profile";
    }

    @RequestMapping("/saved")
    public String getSavedPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        model.addAttribute("posts", postService.getShowPostDtoList(new ArrayList<>(user.getSavedPostList()), user));
        model.addAttribute("growingreddits", subredditService.findTopGrowingSubredditList());

        return "profile";
    }
}
