package com.example.login.domain.auth.service;

import com.example.login.domain.auth.exception.PasswordWrongException;
import com.example.login.domain.auth.exception.UserAlreadyExistsException;
import com.example.login.domain.auth.exception.UserNotFoundException;
import com.example.login.domain.auth.presentation.dto.UserSignInRequest;
import com.example.login.domain.auth.presentation.dto.UserSignUpRequest;
import com.example.login.domain.auth.entity.User;
import com.example.login.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public String signIn(UserSignInRequest request) {
        System.out.println("id : " + request.getUserName());
        System.out.println("pw : " + request.getUserPassword());
        User user = userRepository.findById(request.getUserName())
                .orElseThrow(UserNotFoundException::new);

        if(request.getUserPassword().equals(user.getUserPassword())) {
            return user.getUserName();
        } else {
            throw new PasswordWrongException();
        }
    }

    @Transactional
    public String signUp(UserSignUpRequest request) {

       userRepository.findById(request.getUserName())
                .ifPresent(m -> {
                    throw new UserAlreadyExistsException();
                });

       User user = User.builder()
               .userName(request.getUserName())
               .userPassword(request.getUserPassword())
               .posts(new ArrayList<>())
               .build();
       user = userRepository.save(user);

       return user.getUserName();

    }

}
