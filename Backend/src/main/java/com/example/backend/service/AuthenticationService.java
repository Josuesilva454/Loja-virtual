package com.example.backend.service;

import com.example.backend.model.AuthenticationToken;
import com.example.backend.model.User;
import com.example.backend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationtoken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
    return tokenRepository.findByUser(user);
    }

}
