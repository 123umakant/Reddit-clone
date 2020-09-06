package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.ResponsePostDto;
import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.repository.CommentRepository;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceimpl implements PostService {

    @Autowired
    private SubredditRepository subredditRepository;

    @Autowired
    private CommentRepository commentRepository;

    private PostRepository postRepository;
    public PostServiceimpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post, TextPostDto textPostDto) {

     Subreddit subreddit= subredditRepository.findBycommunityName(textPostDto.getSubredditName());
        post.setSubreddit(subreddit);
        return postRepository.save(post);
    }

    @Override
    public List<Post>  findAll() {
        return postRepository.findAll();
    }
}
