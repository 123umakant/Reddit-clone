package com.reddit.clone.controller;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import com.reddit.clone.service.SubredditService;
import com.reddit.clone.service.UserService;
import com.reddit.clone.utility.TimestampToDays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/subreddit")
public class SubRedditController {

    @Autowired
    SubredditService subredditService;

    @Autowired
    UserService userService;

    @GetMapping("/create")
    public String subredditPage() {
        return "subreddit";
    }

    @PostMapping("/store")
    public String store(@Validated SubredditDto subredditDto, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        subredditService.save(subredditDto, user);
        return "redirect:/";

    }

    @PostMapping("/create")
    public String create(@Validated SubredditDto subredditDto, Principal principal) {
        User user = userService.findByUserName(principal.getName());

        subredditService.save(subredditDto, user);

        return "home";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<Subreddit> getAllSubreddit() {
        return subredditService.findAll();
    }

    @GetMapping("/community/*")
    public String main(@RequestParam("id") String id, Model model) {
        TimestampToDays timestampToDays = new TimestampToDays();
       String timeDaysAgo = timestampToDays.getDays(subredditService.findById(id).get().getCreatedAt().toEpochMilli());
        model.addAttribute("subreddit", subredditService.findById(id).get());
        model.addAttribute("subredditTime", timeDaysAgo);
        return "communitypost";
    }

    @GetMapping("/join")
    public String joinSubreddit(@RequestParam("subredditid") String subredditId, Principal principal){

        User user = userService.findByUserName(principal.getName());
        user.getJoinedCommunitieList().add( subredditService.findById(subredditId).get());
        userService.save(user);

        return "redirect:/all";
    }

    @GetMapping("/unjoin")
    public String unJoinSubreddit(@RequestParam("subredditid") String subredditId, Principal principal){

        User user = userService.findByUserName(principal.getName());
        user.getJoinedCommunitieList().remove( subredditService.findById(subredditId).get());
        userService.save(user);

        return "redirect:/all";
    }

}
