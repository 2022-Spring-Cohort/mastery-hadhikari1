package com.survivingcodingbootcamp.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.Collection;



@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;
    private String hashtag;

    @ManyToMany
    private Collection<Post> posts;

    public Hashtag(String hashtag, Post ...posts) {
        this.hashtag = hashtag;
        this.posts = Arrays.asList(posts);
    }

    public Hashtag() {
    }

    public Long getId() {
        return id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPost(Post post) {
        this.posts.add(post);
    }
}
