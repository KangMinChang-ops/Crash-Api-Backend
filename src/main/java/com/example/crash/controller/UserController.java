package com.example.crash.controller;

import com.example.crash.model.user.UserAuthenticationResponse;
import com.example.crash.model.user.UserLoginPostRequestBody;
import com.example.crash.model.user.UserSignUpPostRequestBody;
import com.example.crash.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crash.model.user.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> signUp(@Valid @RequestBody UserSignUpPostRequestBody userSignUpPostRequestBody) {

        var user = userService.signUp(userSignUpPostRequestBody);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> authenticate(@Valid @RequestBody UserLoginPostRequestBody userLoginPostRequestBody) {
        var response = userService.authenticate(userLoginPostRequestBody);
        return  ResponseEntity.ok(response);
    }


}
