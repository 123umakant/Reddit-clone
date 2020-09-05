package com.reddit.clone.repository;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote findByPostAndUser(Post post, User user);

}
