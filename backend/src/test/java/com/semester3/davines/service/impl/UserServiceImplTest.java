package com.semester3.davines.service.impl;

import com.semester3.davines.domain.AccessToken;
import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.domain.requests.UpdateUserRequest;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.UserEntity;
import com.semester3.davines.repository.entity.UserRoleEntity;
import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessToken requestAccessToken;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity user;

    //TODO: Use UserConverter
    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .email("test@gmail.com")
                .userRoles(Set.of(
                        UserRoleEntity.builder()
                                .role(UserRoleEnum.USER)
                                .build()))
                .password("password")
                .build();

        user.setId(1L);
    }

    @Test
    void createUser() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn("password");

        CreateUserRequest request = CreateUserRequest.builder()
                        .email("test@gmail.com")
                                .password("password")
                                        .build();

        CreateUserResponse response = userService.createUser(request);

        assertEquals(user.getId(), response.getUserId());
        verify(userRepository).save(any());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void getUser() {
        //TODO: Implement this test
    }


    @Test
    void updateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(requestAccessToken.hasRole(any())).thenReturn(true);
        when(userRepository.save(any())).thenReturn(user);

        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .email("test@gmail.com")
                .name("test")
                .build();

        userService.updateUser(request);

        verify(userRepository).save(any());
    }

    //TODO: getUser case with exceptions

    //TODO: updateUser case with exceptions

    //TODO: createUser case with exceptions
}