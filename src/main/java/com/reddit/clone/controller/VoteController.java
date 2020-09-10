package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.UserService;
import com.reddit.clone.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/posts/{postid}")
public class VoteController {

    private PostService postService;
    private VoteService voteService;
    private UserService userService;

    public VoteController(PostService postService, VoteService voteService, UserService userService) {
        this.postService = postService;
        this.voteService = voteService;
        this.userService = userService;
    }

    @RequestMapping("/vote")
    public String vote(@PathVariable("postid") long postId, @RequestParam("upvote") String upVote,
                       Principal principal, HttpServletRequest request) {
        boolean isUpVote = upVote.equals("true") ? true : false;

        Post post = postService.findByPostId(postId);
        User loggedUser = userService.findByUserName(principal.getName());
        Vote vote = null;

        vote = voteService.findByPostAndUser(post, loggedUser);

        if (vote == null) {

            if (isUpVote) {
                post.setUpVoteCount(post.getUpVoteCount() + 1);
            } else {
                post.setDownVoteCount(post.getDownVoteCount() + 1);
            }

            vote = new Vote(isUpVote, post, loggedUser);
            post.getVoteList().add(vote);
            loggedUser.getVoteList().add(vote);

        } else {
            if (isUpVote) {
                post.setDownVoteCount(post.getDownVoteCount() - 1);
                post.setUpVoteCount(post.getUpVoteCount() + 1);
            } else {
                post.setDownVoteCount(post.getDownVoteCount() + 1);
                post.setUpVoteCount(post.getUpVoteCount() - 1);
            }

            vote.setUpVote(isUpVote);
        }

        voteService.save(vote);

        return "redirect:/posts/show";
    }

    @RequestMapping("/unvote")
    public String unVote(@PathVariable("postid") long postId,
                         Principal principal) {

        Post post = postService.findByPostId(postId);
        User loggedUser = userService.findByUserName(principal.getName());

        Vote vote = voteService.findByPostAndUser(post, loggedUser);

        if (vote.isUpVote()) {
            post.setUpVoteCount(post.getUpVoteCount() - 1);
        }else {
            post.setDownVoteCount(post.getDownVoteCount() - 1);
        }

        voteService.removeByPostAndUser(post, loggedUser);

        return "redirect:/posts/show";
    }

}
