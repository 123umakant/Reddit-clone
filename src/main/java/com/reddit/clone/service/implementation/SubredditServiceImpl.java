package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.SubredditService;
import com.reddit.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SubredditServiceImpl implements SubredditService {

    @Autowired
    SubredditRepository subredditRepository;

    @Autowired
    UserService userService;

    @Override
    public Subreddit save(SubredditDto subredditDto, User user) {
        Subreddit subreddit = new Subreddit();
        subreddit.setCommunityName(subredditDto.getTitle());
        subreddit.setDescription(subredditDto.getDescription());
        subreddit.setCreatedAt(Instant.now());
        subreddit.setUser(user);

        return subredditRepository.save(subreddit);

    }

    @Override
    public void saveSubredditPosts(Subreddit subreddit) {
        subredditRepository.save(subreddit);
    }

    @Override
    public Optional<Subreddit> findById(String id) {
      return   subredditRepository.findById(Long.parseLong(id));
    }

    @Override
    public List<Subreddit> findTopGrowingSubredditList() {
        Pageable pageable = PageRequest.of(0, 5);
        return subredditRepository.findTopGrowingSubredditList(pageable);
    }

    @Override
    public List<Subreddit> findAll() {
        return subredditRepository.findAll();
    }
}
