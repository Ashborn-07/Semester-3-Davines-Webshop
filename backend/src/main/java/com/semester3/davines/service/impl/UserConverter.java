package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.User;
import com.semester3.davines.repository.entity.UserEntity;

import java.util.stream.Collectors;

final class UserConverter {

    private UserConverter() {}

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getUserRoles().stream()
                        .map(userRole -> userRole.getRole().toString())
                        .collect(Collectors.toSet()))
                .build();
    }
}
