package com.reddit.clone.service;


import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;

import java.security.Principal;
import java.util.List;

public interface SubredditService {

    Subreddit save(SubredditDto subredditDto, Principal principal);
    List<Subreddit> findAll();

    void saveSubredditPosts(Subreddit subreddit);
}
