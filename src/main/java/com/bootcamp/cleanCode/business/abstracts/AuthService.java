package com.bootcamp.cleanCode.business.abstracts;

import com.bootcamp.cleanCode.business.concretes.requests.loginRequests.LoginRequest;
import com.bootcamp.cleanCode.business.concretes.requests.registerRequests.RegisterRequest;
import com.bootcamp.cleanCode.business.concretes.responses.loginResponses.LoginResponse;

public interface AuthService {
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
