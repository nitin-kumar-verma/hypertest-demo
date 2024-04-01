package com.demo.service;

import com.demo.model.Post;


public interface PostService {
    //This method saves post and returns saved postId
    public String savePost(Post post);

    public String invokeWorldTimeAPI();


}
