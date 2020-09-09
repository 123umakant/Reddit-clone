package com.reddit.clone.service;


import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;

import java.util.List;

public interface SubredditService {

    Subreddit save(SubredditDto subredditDto, User user);

    List<Subreddit> findAll();

}
