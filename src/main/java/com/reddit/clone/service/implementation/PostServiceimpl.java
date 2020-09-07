package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.PostService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepository;
    private SubredditRepository subredditRepository;

    public PostServiceimpl(PostRepository postRepository, SubredditRepository subredditRepository) {
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
    }

    @Override
    public Post save(Post post, TextPostDto textPostDto) {

        Subreddit subreddit= subredditRepository.findBycommunityName(textPostDto.getSubredditName());
        post.setSubreddit(subreddit);
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

    @Override
    public List<Post> findByUser(User user, Sort sort) {
        return postRepository.findByUser(user, sort);
    }
}
