package com.reddit.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", length = 300)
    private String title;

    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "contenttype")
    private String contentType;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = LAZY, mappedBy = "post")
    private Set<Comment> commentList = new HashSet<>();

    @OneToMany(fetch = LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Vote> voteList = new HashSet<>();

    @Column(name = "upvotecount")
    private Integer upVoteCount = 0;

    @Column(name = "downvotecount")
    private Integer downVoteCount = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ManyToOne(fetch =LAZY)
    @JoinColumn(name = "subredditId", referencedColumnName = "id")
    private Subreddit subreddit;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    public Post() {
    }

    public Post(String title, String content, String contentType) {
        this.title = title;
        this.content = content;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Set<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(Set<Comment> commentList) {
        this.commentList = commentList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setVoteList(Set<Vote> voteList) {
        this.voteList = voteList;
    }

    public Integer getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(Integer upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public Integer getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(Integer downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Vote> getVoteList() {
        return voteList;
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", user=" + user +
                ", commentList=" + commentList +
                ", voteList=" + voteList +
                ", upVoteCount=" + upVoteCount +
                ", downVoteCount=" + downVoteCount +
                ", createdAt=" + createdAt +
                ", subreddit=" + subreddit +
                '}';
    }
}
