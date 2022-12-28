package com.semester3.davines.controller;

import com.semester3.davines.domain.NotificationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class NotificationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private NotificationMessage message;

    @BeforeEach
    void setUp() {
        message = new NotificationMessage();
        message.setId("1");
        message.setFrom("jesus");
        message.setTo("St. Peter");
        message.setText("fuck off saint");
    }

    @Test
    void sendNotificationToUsers() throws Exception {
        mockMvc.perform(post("/notifications")
                .contentType("application/json")
                .content("""
                        {
                        "id": "1",
                        "from": "jesus",
                        "to": "St. Peter",
                        "text": "fuck off saint"
                        }
                        """))
                .andExpect(status().isCreated());
    }
}