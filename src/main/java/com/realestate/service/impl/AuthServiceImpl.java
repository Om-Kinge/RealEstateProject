package com.realestate.service.impl;

import com.realestate.dto.AuthResponse;
import com.realestate.dto.LoginRequest;
import com.realestate.dto.RegisterRequest;
import com.realestate.entity.RefreshToken;
import com.realestate.entity.Role;
import com.realestate.entity.SellerStatus;
import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import com.realestate.util.JwtUtil;
import com.realestate.service.AuthService;
import com.realestate.service.RefreshTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthServiceImpl(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }


    // REGISTER USER
    @Override
    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        if (request.getRole() == Role.SELLER) {
            user.setSellerStatus(SellerStatus.PENDING);
            user.setPaid(false);
        }

        userRepository.save(user);

        return "User registered successfully";
    }


    // LOGIN USER
    @Override
    public  AuthResponse login(LoginRequest request) {

    	 User user = userRepository.findByEmail(request.getEmail())
    	            .orElseThrow(() -> new RuntimeException("User not found"));

    	    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
    	        throw new RuntimeException("Invalid credentials");
    	    }

    	    String accessToken = jwtUtil.generateToken(user.getEmail());

    	    RefreshToken refreshToken =
    	            refreshTokenService.createRefreshToken(user.getId());

    	    return new AuthResponse(accessToken, refreshToken.getToken());
    }
}