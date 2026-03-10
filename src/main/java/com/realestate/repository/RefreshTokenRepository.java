package com.realestate.repository;

import com.realestate.entity.RefreshToken;
import com.realestate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    
    Optional<RefreshToken> findByUser(User user);

}
