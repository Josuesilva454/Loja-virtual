package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.SignupDto;
import com.example.backend.exceptions.CustomException;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseDto signUp(SignupDto signupDto){
        // check se usúario entar realmente presente
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
        // nós tem um usúario
        throw new CustomException("Usúario ja presente");
    }
    String encryptedPassword = signupDto.getPassword();
    try {
        encryptedPassword = hashPassword(signupDto.getPassword());
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail() , encryptedPassword );

         userRepository.save(user);
        //criando o token
        ResponseDto responseDto = new ResponseDto();
           responseDto.setStatus("Sucesso");
           responseDto.setMessage("Resposta");
              //salvar o usúario
                return responseDto;
    }

    private String hashPassword(String password)throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
    }




