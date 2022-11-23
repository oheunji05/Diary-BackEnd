package com.example.login.domain.post.presentation;

import com.example.login.domain.post.presentation.dto.CreatePostRequest;
import com.example.login.domain.post.presentation.dto.ModifyPostRequest;
import com.example.login.domain.post.presentation.dto.response.PostListResponse;
import com.example.login.domain.post.presentation.dto.response.PostResponse;
import com.example.login.domain.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "게시글")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation("게시글을 작성합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{user-id}")
    public Long createPost(
            @PathVariable("user-id") String userId,
            @RequestBody CreatePostRequest request
    ) {
        return postService.createPost(userId, request);
    }

    @ApiOperation("게시글을 수정합니다")
    @PatchMapping("/{post-id}")
    public Long modifyPost(
            @PathVariable("post-id") Long postId,
            @RequestBody ModifyPostRequest request
    ) {
        return postService.modifyPost(postId, request);
    }

    @ApiOperation("게시글을 삭제합니다")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{post-id}")
    public void deletePost(
            @PathVariable("post-id") Long postId
    ) {
        postService.deletePost(postId);
    }

    @ApiOperation("게시글 정보를 불러옵니다")
    @GetMapping("/{post-id}")
    public PostResponse getPost(
            @PathVariable("post-id") Long postId
    ) {
        return postService.getPost(postId);
    }

    @ApiOperation("게시글 목록을 불러옵니다")
    @GetMapping("/")
    public PostListResponse getPostList() {
        return postService.getPostList();
    }

}
