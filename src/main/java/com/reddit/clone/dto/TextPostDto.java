package com.reddit.clone.dto;

public class TextPostDto {

    private String title;
    private String content;
    private String contentType;

    public TextPostDto() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public TextPostDto(String title, String content, String contentType) {
        this.title = title;
        this.content = content;
        this.contentType = contentType;
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
}
