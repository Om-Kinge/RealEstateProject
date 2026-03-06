package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.entity.Property;
import com.realestate.entity.PropertyStatus;
import com.realestate.entity.SellerStatus;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PropertyRepository propertyRepository;

    //  Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //  Get all pending sellers
    @GetMapping("/pending-sellers")
    public List<User> getPendingSellers() {
        return userRepository.findBySellerStatus(SellerStatus.PENDING);
    }

    // Approve seller
    @PutMapping("/approve-seller/{id}")
    public ResponseEntity<?> approveSeller(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setSellerStatus(SellerStatus.APPROVED);
        userRepository.save(user);

        return ResponseEntity.ok("Seller approved successfully");
    }

    //  Block seller
    @PutMapping("/block-seller/{id}")
    public ResponseEntity<?> blockSeller(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setSellerStatus(SellerStatus.BLOCKED);
        userRepository.save(user);

        return ResponseEntity.ok("Seller blocked successfully");
    }
    
    @PutMapping("/property/{id}/status")
    public Property updatePropertyStatus(@PathVariable Long id, @RequestBody Map<String,String> request){

        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        PropertyStatus status = PropertyStatus.valueOf(request.get("status"));

        property.setStatus(status);

        return propertyRepository.save(property);
    }
}