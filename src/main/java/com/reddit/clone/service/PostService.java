package com.reddit.clone.service;

import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.SortedMap;

public interface PostService {
    Post savePost(Post post, TextPostDto textPostDto);

    Post save(Post post);

    List<Post> findAll();

    Post findByPostId(long postId);

    List<Post> findByUser(User user, Sort sort);

    List<Post> findSortedPosts(String sort);

    List<ShowPostDto> findSortedAllPosts(String sort, User user);

    List<ShowPostDto> getShowPostDtoList(List<Post> postList, User user);

    void delete(Post post);
}
