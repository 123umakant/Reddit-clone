package com.reddit.clone.service;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteService {

    void save(Vote vote);

    Vote findByPostAndUser(Post post, User user);

    void removeByPostAndUser(Post post, User user);

    List<Post> findUpVotesByUser(@Param("user") User user);

    List<Post> findDownVotesByUser(@Param("user") User user);


}
