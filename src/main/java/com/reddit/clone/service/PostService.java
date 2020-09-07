package com.reddit.clone.service;

import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post, TextPostDto textPostDto);
    Post save(Post post);
    List<Post> findAll();

    Post findByPostId(long postId);
}
