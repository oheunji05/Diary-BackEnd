package com.example.login.domain.post.exception;

import com.example.login.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends BusinessException {
    public PostNotFoundException() {
        super(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다");
    }
}
