package com.reddit.clone.controller;

import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
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

    PostService postService;
    UserService userService;
    VoteService voteService;

    public ProfileController(PostService postService, UserService userService, VoteService voteService) {
        this.postService = postService;
        this.userService = userService;
        this.voteService = voteService;
    }

    @RequestMapping("/")
    public String goToProfile(@RequestParam(value = "sort", required = false) String sort, Principal principal,
                              Model model) {
        User user = userService.findByUserName(principal.getName());

        List<Post> postList = postService.findByUser(user, Sort.by(Sort.Direction.ASC, "createdAt"));

        List<ShowPostDto> showPostDtoList = new ArrayList<>();
        for (Post post : postList) {
            Vote vote = voteService.findByPostAndUser(post, user);
            ShowPostDto showPostDto = new ShowPostDto(post);

            if (vote != null) {
                showPostDto.setIsVoted(true);
                showPostDto.getIsUpVote(vote.isUpVote());
            }
            showPostDtoList.add(showPostDto);
        }

        model.addAttribute("posts", showPostDtoList);

        return "profile";
    }
}
