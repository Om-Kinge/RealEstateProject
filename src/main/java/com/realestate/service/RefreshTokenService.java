package com.realestate.service;

import com.realestate.entity.RefreshToken;

public interface RefreshTokenService {
    
	RefreshToken createRefreshToken(Long userId);
	
	RefreshToken verifyExpiration(RefreshToken token);
	
	RefreshToken findByToken(String token);
	
}
