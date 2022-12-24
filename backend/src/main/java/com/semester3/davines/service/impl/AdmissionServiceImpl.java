package com.semester3.davines.service.impl;

import com.semester3.davines.domain.requests.LoginRequest;
import com.semester3.davines.domain.response.LoginResponse;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.UserEntity;
import com.semester3.davines.service.AccessTokenEncoder;
import com.semester3.davines.service.AdmissionService;
import com.semester3.davines.service.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        UserEntity user = this.userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private boolean matchesPassword(String password, String password1) {
        return passwordEncoder.matches(password, password1);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = user.getId();
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                com.semester3.davines.domain.AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .userId(userId)
                        .build());
    }
}
