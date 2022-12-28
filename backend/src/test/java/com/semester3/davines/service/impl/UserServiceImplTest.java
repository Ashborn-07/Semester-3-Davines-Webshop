package com.semester3.davines.service.impl;

import com.semester3.davines.domain.AccessToken;
import com.semester3.davines.domain.User;
import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.domain.requests.UpdateUserRequest;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.UserEntity;
import com.semester3.davines.repository.entity.UserRoleEntity;
import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import com.semester3.davines.service.exception.EmailAlreadyUsedException;
import com.semester3.davines.service.exception.InvalidUserException;
import com.semester3.davines.service.exception.UnauthorizedDataAccessException;
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
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(false);
        when(requestAccessToken.getUserId())
                .thenReturn(1L);
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));

        Optional<User> userOptional = userService.getUser(1L);

        User expectedUser = UserConverter.convert(user);

        assertTrue(userOptional.isPresent());
        assertEquals(expectedUser, userOptional.get());
        assertEquals(user.getId(), userOptional.get().getId());
        assertEquals(user.getEmail(), userOptional.get().getEmail());
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

    @Test
    void getUser_ShouldThrowException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(false);
        when(requestAccessToken.getUserId())
                .thenReturn(2L);

        assertThrows(UnauthorizedDataAccessException.class, () -> userService.getUser(1L));
    }

    @Test
    void updateUser_ShouldThrowExceptionUnauthorizedDataAccessException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(false);
        when(requestAccessToken.getUserId())
                .thenReturn(2L);

        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .email("fail@test.com")
                .name("name")
                .build();

        assertThrows(UnauthorizedDataAccessException.class, () -> userService.updateUser(request));
    }

    @Test
    void updateUser_ShouldThrowExceptionInvalidUserException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(true);
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .email("fail@test.com")
                .name("name")
                .build();

        assertThrows(InvalidUserException.class, () -> userService.updateUser(request));
    }

    @Test
    void createUser_ShouldThrowExceptionEmailAlreadyUsedException() {
        when(userRepository.existsByEmail(any()))
                .thenReturn(true);

        CreateUserRequest request = CreateUserRequest.builder()
                .email("itsTaken@gmail.com")
                .password("passwordTaken")
                .name("Taken")
                .build();

        assertThrows(EmailAlreadyUsedException.class, () -> userService.createUser(request));
    }
}