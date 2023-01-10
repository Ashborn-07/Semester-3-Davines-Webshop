package com.semester3.davines.controller;

import com.semester3.davines.configuration.security.isauthenticated.IsAuthenticated;
import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.domain.requests.UpdateUserRequest;
import com.semester3.davines.domain.models.User;
import com.semester3.davines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/details/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable(value = "id") final Long id) {
        final Optional<User> user = this.userService.getUser(id);

        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = this.userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") long id, @RequestBody @Valid UpdateUserRequest request) {
        request.setId(id);
        this.userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    //TODO: add method to get all users in pagination
}
