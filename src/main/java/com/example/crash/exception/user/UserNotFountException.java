package com.example.crash.exception.user;

import com.example.crash.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class UserNotFountException  extends ClientErrorException {
    public UserNotFountException() {
        super(HttpStatus.NOT_FOUND, "User not fount");
    }

    public UserNotFountException(String username) {
        super(HttpStatus.NOT_FOUND, "User with name " + username + " not found");
    }
}
