package com.reddit.clone.service;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;

public interface VoteService {

    void save(Vote vote);

    Vote findByPostAndUser(Post post, User user);

}
