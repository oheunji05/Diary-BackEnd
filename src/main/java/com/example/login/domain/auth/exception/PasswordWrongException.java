package com.example.login.domain.auth.exception;

import com.example.login.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PasswordWrongException extends BusinessException {
    public PasswordWrongException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다");
    }
}
