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

import java.security.Principal;
import java.util.Optional;

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
    public String vote(@PathVariable("postid") Long postId, @RequestParam("upvote") String upVote,
                       Principal principal) {

        boolean isUpVote = upVote.equals("true") ? true : false;

        Optional<Post> post = postService.findByPostId(postId);
        Post postValues = post.get();
        User loggedUser = userService.findByUserName(principal.getName());
        Vote vote = null;

        vote = voteService.findByPostAndUser(postValues, loggedUser);

        if (vote == null) {

            if (isUpVote) {
                postValues.setUpVoteCount(postValues.getUpVoteCount() + 1);
            } else {
                postValues.setDownVoteCount(postValues.getDownVoteCount() + 1);
            }

            vote = new Vote(isUpVote, postValues, loggedUser);
            postValues.getVoteList().add(vote);
            loggedUser.getVoteList().add(vote);

        } else {
            if (isUpVote) {
                postValues.setDownVoteCount(postValues.getDownVoteCount() - 1);
                postValues.setUpVoteCount(postValues.getUpVoteCount() + 1);
            } else {
                postValues.setDownVoteCount(postValues.getDownVoteCount() + 1);
                postValues.setUpVoteCount(postValues.getUpVoteCount() - 1);
            }

            vote.setUpVote(isUpVote);
        }

        voteService.save(vote);

        return "redirect:/posts/show";
    }

    @RequestMapping("/unvote")
    public String unVote(@PathVariable("postid") long postId,
                         Principal principal) {

        Optional<Post> postOptional = postService.findByPostId(postId);
        Post post = postOptional.get();
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
