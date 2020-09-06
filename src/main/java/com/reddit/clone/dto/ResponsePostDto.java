package com.reddit.clone.dto;

import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;

import java.util.List;
import java.util.Optional;

public class ResponsePostDto {

    private List<Post> posts;
    private List<Optional<Comment>> optionals;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Optional<Comment>> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<Optional<Comment>> optionals) {
        this.optionals = optionals;
    }

    @Override
    public String toString() {
        return "ResponsePostDto{" +
                "posts=" + posts +
                ", optionals=" + optionals +
                '}';
    }
}
