package com.reddit.clone.repository;

import com.reddit.clone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Subreddit findBycommunityName(String subredditName);
}
