package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Post savePost(Post post, TextPostDto textPostDto) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();

    }

    @Override
    public Optional<Post> findByPostId(Long postId) {
        return postRepository.findById(postId);
    }

}
