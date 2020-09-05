package com.reddit.clone.repository;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote findByPostAndUser(Post post, User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Vote vote WHERE vote.post = :post AND vote.user = :user")
    void removeByPostAndUser(@Param("post") Post post,@Param("user") User user);

}
