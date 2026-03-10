package com.realestate.controller;

import com.realestate.dto.AuthResponse;
import com.realestate.dto.LoginRequest;
import com.realestate.dto.RefreshTokenRequest;
import com.realestate.dto.RegisterRequest;
import com.realestate.entity.RefreshToken;
import com.realestate.service.AuthService;
import com.realestate.service.RefreshTokenService;
import com.realestate.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public String register(@Valid @RequestBody RegisterRequest request) {

		authService.register(request);

		return "User Registered Successfully";
	}

	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody LoginRequest request) {

		return authService.login(request);
	}
	
	@PostMapping("/refresh")
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request){

	    RefreshToken refreshToken =
	            refreshTokenService.findByToken(request.getRefreshToken());

	    refreshTokenService.verifyExpiration(refreshToken);

	    String accessToken =
	            jwtUtil.generateToken(refreshToken.getUser().getEmail());

	    return new AuthResponse(accessToken, request.getRefreshToken());
	}
}