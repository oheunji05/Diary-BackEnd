package com.example.login.domain.auth.exception;

import com.example.login.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 유저입니다");
    }
}
