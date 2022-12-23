package com.semester3.davines.controller;

import com.semester3.davines.domain.LoginRequest;
import com.semester3.davines.domain.LoginResponse;
import com.semester3.davines.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void login_successful() throws Exception {
        LoginRequest expectedRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        when(loginService.login(expectedRequest)).thenReturn(LoginResponse.builder()
                .accessToken("token")
                .build());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                            "email": "test@gmail.com",
                            "password": "password"
                            }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token"));

        verify(loginService).login(expectedRequest);
    }

    @Test
    void login_unsuccessfulNoEmail() {
        //TODO: Implement
    }

    @Test
    void login_unsuccessfulPasswordNoMatch() {
        //TODO: Implement
    }
}