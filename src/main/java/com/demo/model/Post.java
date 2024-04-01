package com.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "postName")
    private String postName;
    @Column(name = "postContents")
    private String postContents;

    public Long getId() {
        return id;
    }

    public Post(Long id, String postName, String postContents) {
        this.id = id;
        this.postName = postName;
        this.postContents = postContents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }
}
