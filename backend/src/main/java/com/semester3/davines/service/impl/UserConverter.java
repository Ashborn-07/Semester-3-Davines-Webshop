package com.semester3.davines.service.impl;

import com.semester3.davines.domain.User;
import com.semester3.davines.repository.entity.UserEntity;

final class UserConverter {

    private UserConverter() {}

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
