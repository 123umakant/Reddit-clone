package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getHotIndexPage(Model model, Principal principal) {

        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts(getSortString("hot"));

        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping("/new")
    public String getNewPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts(getSortString("new"));

        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping("/top")
    public String getTopPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts(getSortString("top"));

        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping("/best")
    public String getRisingPostIndexPage(Model model, Principal principal) {
        if (principal != null) {
            return "profile";
        }

        List<Post> postList = postService.findSortedPosts(getSortString("best"));

        model.addAttribute("posts", postList);
        return "index";
    }

    private String getSortString(String sort) {
        String sortBy = null;

        if(sort.equals("hot")){
            sortBy = "(post.upVoteCount/post.downVoteCount)";
        }

        switch (sort){
            case "best" :
                sortBy = "(post.upVoteCount/post.downVoteCount)";
                break;
            case "new" :
                sortBy = "post.createdAt";
                break;
            case "top" :
                sortBy = "(post.upVoteCount - post.downVoteCount)";
                break;
            case  "hot" :
                sortBy = "post.upVoteCount*(EXTRACT(EPOCH FROM (NOW()::timestamp - post.createdAt))";
                break;
            default:
                sortBy = "post.createdAt";
        }

        return sortBy;
    }
}
