package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.AccessToken;
import com.semester3.davines.domain.models.User;
import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.requests.UpdateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.UserEntity;
import com.semester3.davines.repository.entity.UserRoleEntity;
import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import com.semester3.davines.service.UserService;
import com.semester3.davines.service.exception.EmailAlreadyUsedException;
import com.semester3.davines.service.exception.InvalidUserException;
import com.semester3.davines.service.exception.UnauthorizedDataAccessException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private AccessToken requestAccessToken;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsedException();
        }

        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthday(request.getBirthday())
                .phoneNumber(request.getPhoneNumber())
                .password(encodedPassword)
                .userRoles(Set.of(
                        UserRoleEntity.builder()
                                .role(UserRoleEnum.USER)
                                .build()))
                .build();

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        if (!requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()) && !requestAccessToken.getUserId().equals(userId)) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        return this.userRepository.findById(userId).map(UserConverter::convert);
    }

    @Override
    public void updateUser(UpdateUserRequest request) {
        if (!requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()) && !requestAccessToken.getUserId().equals(request.getId())) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        Optional<UserEntity> userOptional = this.userRepository.findById(request.getId());

        if (userOptional.isEmpty()) {
            throw new InvalidUserException();
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());

        this.userRepository.save(user);
    }
}
