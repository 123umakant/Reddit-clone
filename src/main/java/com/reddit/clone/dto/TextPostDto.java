package com.reddit.clone.dto;

import com.reddit.clone.model.Subreddit;

public class TextPostDto {

    private Long id;
    private String title;
    private String content;
    private String contentType;
    private String subredditName;


    public TextPostDto() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }



    public String getSubredditName() {
        return subredditName;
    }

    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
    }

    public TextPostDto(String title, String content, String contentType) {
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

    @Override
    public String toString() {
        return "TextPostDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", subredditName='" + subredditName + '\'' +
                '}';
    }
}
