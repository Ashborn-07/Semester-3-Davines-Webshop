package com.semester3.davines.controller;

import com.semester3.davines.configuration.security.isauthenticated.IsAuthenticated;
import com.semester3.davines.domain.CreateUserRequest;
import com.semester3.davines.domain.CreateUserResponse;
import com.semester3.davines.domain.UpdateUserRequest;
import com.semester3.davines.domain.User;
import com.semester3.davines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //TODO: create get method to get user data

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = this.userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_USER"})
    @PutMapping("{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") long id, @RequestBody @Valid UpdateUserRequest request) {
        request.setId(id);
        this.userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }
}
