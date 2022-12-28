package com.semester3.davines.controller;

import com.semester3.davines.domain.requests.LoginRequest;
import com.semester3.davines.domain.response.LoginResponse;
import com.semester3.davines.service.AdmissionService;
import com.semester3.davines.service.exception.InvalidCredentialsException;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class AdmissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdmissionService loginService;

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
    void login_unsuccessfulNoEmail() throws Exception {
        LoginRequest expectedRequest = LoginRequest.builder()
                .email("asd@email.com")
                .password("password")
                .build();

        when(loginService.login(expectedRequest))
                .thenThrow(new InvalidCredentialsException());

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                    "email": "asd@email.com",
                                    "password": "password"
                                    }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("INVALID_CREDENTIALS")))
                .andReturn();

        verify(loginService).login(expectedRequest);
    }

    //TODO: If possible make a test for login_unsuccessfulPasswordDontMatch
}