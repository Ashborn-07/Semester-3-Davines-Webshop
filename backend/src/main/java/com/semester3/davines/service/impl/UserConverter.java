package com.semester3.davines.service.impl;

import com.semester3.davines.domain.User;
import com.semester3.davines.repository.entity.UserEntity;

import java.util.stream.Collectors;

final class UserConverter {

    private UserConverter() {}

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getUserRoles().stream()
                        .map(userRole -> userRole.getRole().toString())
                        .collect(Collectors.toSet()))
                .build();
    }
}
