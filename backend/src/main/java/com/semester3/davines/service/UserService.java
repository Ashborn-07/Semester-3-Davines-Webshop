package com.semester3.davines.service;

import com.semester3.davines.domain.CreateUserRequest;
import com.semester3.davines.domain.CreateUserResponse;
import com.semester3.davines.domain.UpdateUserRequest;
import com.semester3.davines.domain.User;

import java.util.Optional;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

    void deleteUser(Long userId);

    Optional<User> getUser(Long userId);

    void updateUser(UpdateUserRequest request);
}
