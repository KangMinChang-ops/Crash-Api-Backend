package com.example.crash.service;

import com.example.crash.exception.user.UserAlreadyExistsException;
import com.example.crash.exception.user.UserNotFountException;
import com.example.crash.model.entity.UserEntity;
import com.example.crash.model.user.User;
import com.example.crash.model.user.UserAuthenticationResponse;
import com.example.crash.model.user.UserLoginPostRequestBody;
import com.example.crash.model.user.UserSignUpPostRequestBody;
import com.example.crash.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
    }

    public User signUp(UserSignUpPostRequestBody userSignUpPostRequestBody) {
        userEntityRepository.findByUsername(userSignUpPostRequestBody.username())
                .ifPresent(
                        user -> {
                            throw new UserAlreadyExistsException();
                        });
        var userEntity =
            userEntityRepository.save(
                    UserEntity.of(
                            userSignUpPostRequestBody.username(),
                            passwordEncoder.encode(userSignUpPostRequestBody.password()),
                            userSignUpPostRequestBody.name(),
                            userSignUpPostRequestBody.email()
                    )
            );
        return User.from(userEntity);
    }

    public UserAuthenticationResponse authenticate(UserLoginPostRequestBody userLoginPostRequestBody) {
        var userEntity = getUserEntityByUsername(userLoginPostRequestBody.username());

        if(passwordEncoder.matches(userLoginPostRequestBody.password(), userEntity.getPassword())) {
            var accessToken = jwtService.generateAccessToken(userEntity);
            return new UserAuthenticationResponse(accessToken);
        }else{
            throw new UserNotFountException();
        }

    }

    public UserEntity getUserEntityByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
