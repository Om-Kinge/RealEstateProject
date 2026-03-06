package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {
    
	@Autowired
    private UserRepository userRepository;

    // Seller Payment API
    @PostMapping("/pay")
    public String makePayment(@RequestParam Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPaid(true);

        userRepository.save(user);

        return "Payment successful. Seller can now post properties.";
    }
}