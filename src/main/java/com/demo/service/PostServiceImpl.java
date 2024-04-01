package com.demo.service;

import com.demo.model.Post;
import com.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Value("${world_time_api_url}")
    private String WorldTimeApiURL;

    @Override
    public String savePost(Post post) {
        return String.valueOf(this.postRepository.save(post).getId());
    }

    @Override
    public String invokeWorldTimeAPI() {
        return new RestTemplate().getForObject(WorldTimeApiURL, String.class);
    }
}
