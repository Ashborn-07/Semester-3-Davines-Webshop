package com.semester3.davines.service.impl;

import com.semester3.davines.domain.requests.LoginRequest;
import com.semester3.davines.domain.response.LoginResponse;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.UserEntity;
import com.semester3.davines.repository.entity.UserRoleEntity;
import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import com.semester3.davines.service.AccessTokenEncoder;
import com.semester3.davines.service.exception.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private AdmissionServiceImpl loginService;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("test")
                .userRoles(Set.of(UserRoleEntity.builder()
                                .role(UserRoleEnum.USER)
                        .build()))
                .build();
    }

    @Test
    void login() {
        when(userRepository.findByEmail(any())).thenReturn(userEntity);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn("token");

        LoginRequest request = LoginRequest.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        LoginResponse response = loginService.login(request);

        verify(userRepository).findByEmail(any());
        verify(passwordEncoder).matches(any(), any());
        verify(accessTokenEncoder).encode(any());
        assertEquals("token", response.getAccessToken());
    }

    @Test
    void login_unsuccessfulEmailNotFount() {
        when(userRepository.findByEmail(anyString()))
                .thenThrow(new InvalidCredentialsException());

        LoginRequest request = LoginRequest.builder()
                .email("failure@gmail.com")
                .password("test")
                .build();

        assertThrows(InvalidCredentialsException.class, () -> loginService.login(request));
    }

    @Test
    void login_unsuccessfulPasswordNotMatch() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        LoginRequest request = LoginRequest.builder()
                .email("failure@gmail.com")
                .password("failed")
                .build();

        assertThrows(InvalidCredentialsException.class, () -> loginService.login(request));
    }
}