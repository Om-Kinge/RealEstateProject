package com.realestate.service;

import com.realestate.dto.LoginRequest;
import com.realestate.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);

}