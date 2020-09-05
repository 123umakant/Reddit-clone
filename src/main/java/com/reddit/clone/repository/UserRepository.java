package com.reddit.clone.repository;

import com.reddit.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User save(User user);

    User findByUserName(String userName);

    @Query(value = "WITH votelisttable(votelist) AS (SELECT user.voteList FROM user_data user), SELECT votelistt.vote_id FROM votelisttable.votelist AS votelistt", nativeQuery = true)
    List<Integer> findVotedIds(@Param("userName") String userName);
}
