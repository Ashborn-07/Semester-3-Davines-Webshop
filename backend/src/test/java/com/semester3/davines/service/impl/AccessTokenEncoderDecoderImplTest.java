//package com.semester3.davines.service.impl;
//
//import com.semester3.davines.domain.models.AccessToken;
//import com.semester3.davines.service.exception.InvalidAccessTokenException;
//import com.semester3.davines.service.exception.InvalidCredentialsException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class AccessTokenEncoderDecoderImplTest {
//
//    //TODO: Implement Unit Tests for AccessTokenEncoderDecoderImpl
//    @Mock
//    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder;
//
//    @BeforeEach
//    void setUp() {
//        AccessToken accessToken;
//        accessToken = AccessToken.builder()
//                .userId(1L)
//                .roles(List.of("ROLE_ADMIN"))
//                .subject("subject")
//                .build();
//    }
//
//    @Test
//    void encode() {
//        //TODO: Implement Unit Test
//    }
//
//    @Test
//    void decode() {
//        //TODO: Implement Unit Test
//    }
//}