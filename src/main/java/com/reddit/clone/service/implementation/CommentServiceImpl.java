package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.CommentDto;
import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.CommentRepository;
import com.reddit.clone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(CommentDto commentDto, User user) {
        Post post = new Post();
        post.setId(commentDto.getPostId());
        Comment comment = new Comment();
        comment.setText(commentDto.getComment());
        comment.setCreatedDate(Instant.now());
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByPostId(String postId) {
       return commentRepository.findBypostId(Long.parseLong(postId));

    }

    @Override
    public List<Comment> findByUser(User user) {
        return commentRepository.findByUser(user);
    }

    @Override
    public void deleteAll(Iterable<Comment> comments) {
        commentRepository.deleteInBatch(comments);
    }

    @Override
    public void deleteByPost(Post post) {
        commentRepository.deleteByPost(post);
    }
}
