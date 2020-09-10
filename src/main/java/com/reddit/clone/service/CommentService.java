package com.reddit.clone.service;

import com.reddit.clone.dto.CommentDto;
import com.reddit.clone.model.Comment;
import java.util.*;
public interface CommentService {

    Comment save(CommentDto commentDto);
    List<Comment> findAll();

    List<Comment> findByPostId(String postId);
}
