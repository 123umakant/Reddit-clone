package com.reddit.clone.repository;

import com.reddit.clone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
