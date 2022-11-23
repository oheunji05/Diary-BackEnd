package com.example.login.domain.auth.presentation;

import com.example.login.domain.auth.presentation.dto.UserSignInRequest;
import com.example.login.domain.auth.presentation.dto.UserSignUpRequest;
import com.example.login.domain.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "유저")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation("회원가입을 합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public String userSignUp(
            @RequestBody UserSignUpRequest request
    ) {
        return authService.signUp(request);
    }

    @ApiOperation("로그인을 합니다")
    @PostMapping("/sign-in")
    public String userSignIn(
            @RequestBody UserSignInRequest request
    ) {
        return authService.signIn(request);
    }

}
