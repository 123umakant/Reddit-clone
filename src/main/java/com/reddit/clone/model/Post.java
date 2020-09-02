package com.reddit.clone.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @Lob
    private String description;
    @Lob
    private String content;
    private Integer voteCount = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private String url;
    private Instant createdDate;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", voteCount=" + voteCount +
                ", user=" + user +
                ", url='" + url + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
