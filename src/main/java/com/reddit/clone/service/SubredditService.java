package com.reddit.clone.service;


import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface SubredditService {

    Subreddit save(SubredditDto subredditDto, User user);

    List<Subreddit> findAll();

    void saveSubredditPosts(Subreddit subreddit);

    Optional<Subreddit> findById(String id);
}
