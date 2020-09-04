package com.reddit.clone.service;


import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;

import java.util.List;

public interface SubredditService {

    Subreddit save(SubredditDto subredditDto);

    List<Subreddit> findAll();

}
