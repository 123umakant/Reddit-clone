package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SubredditImpl implements SubredditService {

    @Autowired
    SubredditRepository subredditRepository;

    @Override
    public Subreddit save(SubredditDto subredditDto) {

        Subreddit subreddit = new Subreddit();
        subreddit.setCommunityName(subredditDto.getTitle());
        subreddit.setDescription(subredditDto.getDescription());
        subreddit.setCreatedAt(Instant.now());

        return subredditRepository.save(subreddit);

    }

    @Override
    public List<Subreddit> findAll() {
        return subredditRepository.findAll();
    }
}
