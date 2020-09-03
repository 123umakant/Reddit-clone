package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.CommentDto;
import com.reddit.clone.model.Comment;
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
    public Comment save(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getComment());
        comment.setCreatedDate(Instant.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
