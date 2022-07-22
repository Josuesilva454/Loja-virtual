package com.example.backend.service;

import com.example.backend.exceptions.AuthenticationFailException;
import com.example.backend.model.AuthenticationToken;
import com.example.backend.model.User;
import com.example.backend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    public User getUser(String token) {
       final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            return null;
        }

        // authenticationToken não está null null
     return authenticationToken.getUser();
    }

    public void authenticate(String token) {
        // verificação nula
        if (Objects.isNull(token)){

            throw  new AuthenticationFailException("Token não presente");
        }
        if (Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token inválido");
        }
    }
}
