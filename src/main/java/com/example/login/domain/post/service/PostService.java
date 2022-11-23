package com.example.login.domain.post.service;

import com.example.login.domain.auth.entity.User;
import com.example.login.domain.auth.exception.UserNotFoundException;
import com.example.login.domain.auth.repository.UserRepository;
import com.example.login.domain.post.entity.Post;
import com.example.login.domain.post.exception.PostNotFoundException;
import com.example.login.domain.post.presentation.dto.CreatePostRequest;
import com.example.login.domain.post.presentation.dto.ModifyPostRequest;
import com.example.login.domain.post.presentation.dto.response.PostListResponse;
import com.example.login.domain.post.presentation.dto.response.PostResponse;
import com.example.login.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createPost(String userId, CreatePostRequest request) {

        User author = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .weather(request.getWeather())
                .date(request.getDate())
                .build();
        author.addPost(post);

        return post.getPostId();
    }

    @Transactional(readOnly = true)
    public PostListResponse getPostList() {

        List<Post> posts = postRepository.findAll();

        List<PostResponse> list = posts.stream().map(it ->
                PostResponse.builder()
                        .postId(it.getPostId())
                        .userName(it.getAuthor().getUserName())
                        .title(it.getTitle())
                        .content(it.getContent())
                        .weather(it.getWeather())
                        .date(it.getDate())
                        .build()
                ).collect(Collectors.toList());

        return PostListResponse.builder()
                .list(list)
                .build();
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return PostResponse.builder()
                .postId(post.getPostId())
                .userName(post.getAuthor().getUserName())
                .title(post.getTitle())
                .content(post.getContent())
                .weather(post.getWeather())
                .date(post.getDate())
                .build();
    }

    @Transactional
    public Long modifyPost(Long postId, ModifyPostRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        post.updatePost(request.getTitle(), request.getContent());
        post.getAuthor().addPost(post);

        return post.getPostId();
    }

    @Transactional
    public void deletePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        post.getAuthor().getPosts().remove(post);


    }

}
