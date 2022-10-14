package com.semester3.davines.service;

import com.semester3.davines.domain.LoginRequest;
import com.semester3.davines.domain.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest request);
}
