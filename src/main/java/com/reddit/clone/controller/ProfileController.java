package com.reddit.clone.controller;

import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.service.CommentService;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import com.reddit.clone.service.VoteService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public ProfileController(PostService postService, UserService userService, VoteService voteService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.voteService = voteService;
        this.commentService = commentService;
    }

    @RequestMapping
    public String goToProfile(@RequestParam(value = "sort", required = false) String sort, Principal principal,
                              Model model) {
        User user = userService.findByUserName(principal.getName());

        List<Post> postList = postService.findByUser(user, Sort.by(Sort.Direction.ASC, "createdAt"));

        model.addAttribute("posts", postService.getShowPostDtoList(postList, user));

        return "profile";
    }

    @RequestMapping("/comments")
    public String getUserComments(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<Comment> commentList = commentService.findByUser(user);

        model.addAttribute("comments", commentList);

        return "profile";
    }

    @RequestMapping("/upvoted")
    public String getUpVotedPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<Post> upVotedPostList = voteService.findUpVotesByUser(user);

        model.addAttribute("posts", postService.getShowPostDtoList(upVotedPostList, user));

        return "profile";
    }

    @RequestMapping("/downvoted")
    public String getDownVotedPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        List<Post> downVotedPostList = voteService.findDownVotesByUser(user);

        model.addAttribute("posts", postService.getShowPostDtoList(downVotedPostList, user));

        return "profile";
    }

    @RequestMapping("/saved")
    public String getSavedPosts(Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());

        model.addAttribute("posts", postService.getShowPostDtoList(new ArrayList<>(user.getSavedPostList()), user));

        return "profile";
    }
}
