package com.example.login.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class PostResponse {

    private Long postId;

    private String userName;

    private String title;

    private String content;

    private String weather;

    private String date;


}
