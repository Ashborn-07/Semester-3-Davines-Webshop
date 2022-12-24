package com.semester3.davines.controller;

import com.semester3.davines.domain.requests.CreateUserRequest;
import com.semester3.davines.domain.response.CreateUserResponse;
import com.semester3.davines.domain.requests.UpdateUserRequest;
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

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(1L);
    }

    @Test
    void createUser() throws Exception {
        CreateUserRequest expectedRequest = CreateUserRequest.builder()
                .email("test@gmail.com")
                .name("test")
                .password("test")
                .build();

        when(userService.createUser(expectedRequest))
                .thenReturn(CreateUserResponse.builder()
                        .userId(1L)
                        .build());

        mockMvc.perform(post("/users")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .content("""
                        {
                            "email": "test@gmail.com",
                            "name": "test",
                            "password": "test"
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
                .name("test2")
                .build();

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .content("""
                        {
                            "email": "test2@gmail.com",
                            "name": "test2"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userService).updateUser(expectedRequest);
    }
}