package com.reddit.clone.service.implementation;

import com.reddit.clone.model.Post;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepository;

    public PostServiceimpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findByPostId(long postId) {
        return postRepository.findById(postId);
    }
}
