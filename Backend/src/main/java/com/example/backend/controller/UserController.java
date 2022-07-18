package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.SignupDto;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("User")
@RestController
public class UserController {

    @Autowired
     UserService userService;
      //duas api

    //signup

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.signUp(signupDto);
    }
}
