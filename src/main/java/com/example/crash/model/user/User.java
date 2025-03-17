package com.example.crash.model.user;

import com.example.crash.model.entity.UserEntity;

//JPA 에서 데이터 방아올 때 민감한 사항은 제외하고 받기위해 RECORD 작성
public record User(Long userId, String username, String name, String email) {
    public static User from(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getName(),
                userEntity.getEmail());
    }
}
