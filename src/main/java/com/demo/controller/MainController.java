package com.demo.controller;

import com.demo.model.Post;
import com.demo.model.Response;
import com.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @Autowired
    PostService hts;

    @RequestMapping(value = "/api/createPost", method = RequestMethod.POST)
    public Response createNewPost(@RequestBody Post post) {

        return new Response(String.valueOf(hts.savePost(post)), hts.invokeWorldTimeAPI());
    }
}






