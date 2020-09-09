package com.reddit.clone.dto;

import com.reddit.clone.model.Comment;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ShowPostDto {

    private Long id;

    private String title;

    private String content;

    private String contentType;

    private User user;

    private Set<Comment> commentList = new HashSet<>();

    private Integer upVoteCount = 0;

    private Integer downVoteCount = 0;

    private Date createdAt;

    private boolean isVoted;

    private boolean isUpVote;

    private boolean isSaved;

    public ShowPostDto() {
    }

    public ShowPostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.contentType = post.getContentType();
        this.user = post.getUser();
        this.upVoteCount = post.getUpVoteCount();
        this.downVoteCount = post.getDownVoteCount();
        this.createdAt = post.getCreatedAt();
        this.isVoted = false;
        this.isSaved = false;
    }

    public ShowPostDto(Long id, String title, String content, String contentType, User user, Set<Comment> commentList, Integer upVoteCount, Integer downVoteCount, Date createdAt, boolean isVoted, boolean isUpVote) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.contentType = contentType;
        this.user = user;
        this.commentList = commentList;
        this.upVoteCount = upVoteCount;
        this.downVoteCount = downVoteCount;
        this.createdAt = createdAt;
        this.isVoted = isVoted;
        this.isUpVote = isUpVote;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(Set<Comment> commentList) {
        this.commentList = commentList;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(boolean voted) {
        isVoted = voted;
    }

    public boolean getIsUpVote() {
        return isUpVote;
    }

    public void getIsUpVote(boolean upVote) {
        isUpVote = upVote;
    }


    public boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean saved) {
        isSaved = saved;
    }
}
