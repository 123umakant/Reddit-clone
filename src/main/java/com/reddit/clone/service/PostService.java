package com.reddit.clone.service;

import com.reddit.clone.model.Post;

import java.util.List;

public interface PostService {
    Post save(Post post);

    List<Post> findAll();
}
