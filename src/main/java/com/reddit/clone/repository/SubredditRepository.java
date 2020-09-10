package com.reddit.clone.repository;

import com.reddit.clone.model.Subreddit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Subreddit findBycommunityName(String subredditName);

    @Query(value = "SELECT sub From Subreddit sub ORDER BY (sub.subscriberCount * EXTRACT(EPOCH FROM (NOW() - sub.createdAt))) ASC")
    List<Subreddit> findTopGrowingSubredditList(Pageable pageable);
}
