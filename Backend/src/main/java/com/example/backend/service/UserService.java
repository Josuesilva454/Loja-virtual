package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.SignInDto;
import com.example.backend.dto.user.SignInReponseDto;
import com.example.backend.dto.user.SignupDto;
import com.example.backend.exceptions.AuthenticationFailException;
import com.example.backend.exceptions.CustomException;
import com.example.backend.model.AuthenticationToken;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
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
                signupDto.getEmail(), encryptedPassword);

        userRepository.save(user);
        //criando o token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationtoken(authenticationToken);


        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("Sucesso");
        responseDto.setMessage("Usuário criado com sucesso");
        //salvar o usúario
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public SignInReponseDto signIn(SignInDto signInDto) {
        ResponseDto responseDto = new ResponseDto();
        //find usuário eo email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException(("usuário não estar inválido"));
        }

        // tenha a senha
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("senha errado");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // compare a senha dentro do banco de dados
        // if password match
        AuthenticationToken token = authenticationService.getToken(user);
       // recuperar o token
        if (Objects.isNull(token)){
          throw new CustomException(("token não estar presente"));
      }
      return new SignInReponseDto( "sucesso", token.getToken());
      // retorna a resposta
    }

}





