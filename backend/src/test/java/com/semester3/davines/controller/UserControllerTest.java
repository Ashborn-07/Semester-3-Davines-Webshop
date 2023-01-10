package com.semester3.davines.controller;

import com.semester3.davines.domain.models.User;
import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.domain.requests.UpdateUserRequest;
import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import com.semester3.davines.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private User user;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("test@gmail.com")
                .firstName("Test")
                .lastName("Test")
                .birthday("12/12/2000")
                .phoneNumber("0123456789")
                .roles(Set.of(UserRoleEnum.ADMIN.name()))
                .id(1L)
                .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/delete/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(1L);
    }

    @Test
    void createUser() throws Exception {
        CreateUserRequest expectedRequest = CreateUserRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .firstName("Test")
                .lastName("Test")
                .birthday("12/12/2000")
                .phoneNumber("0123456789")
                .build();

        when(userService.createUser(expectedRequest))
                .thenReturn(CreateUserResponse.builder()
                        .userId(1L)
                        .build());

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content("""
                                {
                                    "email": "test@gmail.com",
                                    "firstName": "Test",
                                    "lastName": "Test",
                                    "birthday": "12/12/2000",
                                    "phoneNumber": "0123456789",
                                    "password": "password"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "userId": 1
                        }
                        """));

        verify(userService).createUser(expectedRequest);
    }

    @Test
    @WithMockUser(username = "test")
    void updateUser() throws Exception {
        UpdateUserRequest expectedRequest = UpdateUserRequest.builder()
                .id(1L)
                .email("test2@gmail.com")
                .firstName("test2")
                .lastName("Test2")
                .birthday("12/12/2000")
                .phoneNumber("1234567890")
                .build();

        mockMvc.perform(put("/users/update/1")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content("""
                                {
                                    "email": "test2@gmail.com",
                                    "firstName": "test2",
                                    "lastName": "Test2",
                                    "birthday": "12/12/2000",
                                    "phoneNumber": "1234567890"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userService).updateUser(expectedRequest);
    }

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    void getUserDetails() throws Exception {
        when(userService.getUser(1L))
                .thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/details/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "email": "test@gmail.com",
                            "firstName": "Test",
                            "lastName": "Test",
                            "birthday": "12/12/2000",
                            "phoneNumber": "0123456789",
                            "roles": [
                                "ADMIN"
                            ]
                        }
                        """));

        verify(userService).getUser(1L);
    }

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    void getUserDetails_ShouldReturnNotFound() throws Exception {
        when(userService.getUser(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/users/details/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getUser(1L);
    }
}