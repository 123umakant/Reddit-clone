package com.reddit.clone.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long voteId;

    @NotNull
    @Column(name = "isupvote")
    private boolean isUpVote;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Post post;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    public Vote() {
    }

    public Vote(boolean isUpVote, Post post, User user) {
        this.isUpVote = isUpVote;
        this.post = post;
        this.user = user;
    }

    public boolean isUpVote() {
        return isUpVote;
    }

    public void setUpVote(boolean upVote) {
        isUpVote = upVote;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                '}';
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
