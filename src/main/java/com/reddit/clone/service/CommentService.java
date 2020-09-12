package com.reddit.clone.service;

import com.reddit.clone.dto.CommentDto;
import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;

import java.util.*;
public interface CommentService {

    Comment save(CommentDto commentDto, User user);
    List<Comment> findAll();

    List<Comment> findByPostId(String postId);
    List<Comment> findByUser(User user);

    void deleteAll(Iterable< Comment> comments);

    void deleteByPost(Post post);

}
