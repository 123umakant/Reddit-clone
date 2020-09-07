package com.reddit.clone.service;

import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.SortedMap;

public interface PostService {
    Post save(Post post, TextPostDto textPostDto);
    List<Post> findAll();

    Post findByPostId(long postId);

    List<Post> findByUser(User user, Sort sort);
}
