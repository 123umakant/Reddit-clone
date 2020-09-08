package com.reddit.clone.repository;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
     Post findById(long postId);

     List<Post> findByUser(User user, Sort sort);

     @Query(value = "SELECT post FROM Post post ORDER BY (post.upVoteCount * EXTRACT(EPOCH FROM (NOW() - post.createdAt))) DESC")
     List<Post> findHotSortedPosts( );

     @Query(value = "SELECT post FROM Post post ORDER BY (post.upVoteCount - post.downVoteCount) DESC")
     List<Post> findTopSortedPosts( );

     @Query(value = "SELECT post FROM Post post ORDER BY post.createdAt DESC")
     List<Post> findNewSortedPosts( );

     @Query(value = "SELECT post FROM Post post ORDER BY (post.upVoteCount/post.downVoteCount) DESC")
     List<Post> findBestSortedPosts( );

}

//             "(post.upVoteCount * EXTRACT(EPOCH FROM (NOW()::timestamp - post.createdAt))) " +
