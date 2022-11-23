package com.example.login.domain.auth.exception;

import com.example.login.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다");
    }
}
