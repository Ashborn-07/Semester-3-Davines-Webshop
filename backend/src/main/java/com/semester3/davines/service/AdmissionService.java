package com.semester3.davines.service;

import com.semester3.davines.domain.requests.LoginRequest;
import com.semester3.davines.domain.response.LoginResponse;

public interface AdmissionService {

    LoginResponse login(LoginRequest request);
}
