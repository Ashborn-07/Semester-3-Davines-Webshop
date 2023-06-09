package com.semester3.davines.controller;

import com.semester3.davines.domain.requests.LoginRequest;
import com.semester3.davines.domain.response.LoginResponse;
import com.semester3.davines.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService loginService;

    @PostMapping
    public @ResponseBody ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = loginService.login(request);
        return ResponseEntity.ok(response);
    }
}
