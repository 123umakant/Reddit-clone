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

    PostService postService;
    UserService userService;
    VoteService voteService;

    public ProfileController(PostService postService, UserService userService, VoteService voteService) {
        this.postService = postService;
        this.userService = userService;
        this.voteService = voteService;
    }

    @GetMapping
    public String goToProfile(@RequestParam(value = "sort", required = false) String sort, Principal principal,
                              @ModelAttribute("page") String page, HttpServletRequest request,
                              Model model) {
        User user = userService.findByUserName(principal.getName());

        List<Post> postList = postService.findByUser(user, Sort.by(Sort.Direction.DESC, "createdAt"));

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
