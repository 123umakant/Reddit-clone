package com.reddit.clone.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @ManyToOne(fetch = LAZY)
    private Post post;

    @Column(name = "votecount")
    private Integer voteCount = 0;

    @OneToMany(fetch = LAZY, mappedBy = "commentSet")
    private Set<Comment> commentSet;

    private Instant createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate =createdDate ;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", post=" + post +
                ", createdDate=" + createdDate +
                '}';
    }
}
