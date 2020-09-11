package com.reddit.clone.repository.repositoryImpl;


import com.reddit.clone.model.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<Post> fetchSearchedPost(String searched) {

        String query = "select * from post where to_tsvector(content || ' ' || title) @@ to_tsquery('" + searched + "')";
        return entityManager.createNativeQuery(query, Post.class).getResultList();
    }
}
