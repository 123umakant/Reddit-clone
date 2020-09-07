package com.reddit.clone.repository;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
     Post findById(long postId);

     List<Post> findByUser(User user, Sort sort);
}
