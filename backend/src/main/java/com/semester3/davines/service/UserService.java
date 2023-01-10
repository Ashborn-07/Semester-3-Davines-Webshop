package com.semester3.davines.service;

import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.domain.requests.UpdateUserRequest;
import com.semester3.davines.domain.models.User;

import java.util.Optional;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

    void deleteUser(Long userId);

    Optional<User> getUser(Long userId);

    void updateUser(UpdateUserRequest request);
}
