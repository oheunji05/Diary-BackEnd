package com.example.login.domain.auth.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserSignInRequest {

    private String userName;

    private String userPassword;

}
