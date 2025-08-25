package com.bootcamp.cleanCode.webApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.AuthService;
import com.bootcamp.cleanCode.business.concretes.requests.loginRequests.LoginRequest;
import com.bootcamp.cleanCode.business.concretes.requests.registerRequests.RegisterRequest;
import com.bootcamp.cleanCode.business.concretes.responses.loginResponses.LoginResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }
}
