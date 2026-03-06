package com.realestate.service;

import com.realestate.entity.*;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Property createProperty(Property property, Long sellerId){

        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        if(!seller.isPaid()){
            throw new RuntimeException("Seller must complete payment");
        }

        if(seller.getSellerStatus() != SellerStatus.APPROVED){
            throw new RuntimeException("Seller not approved by admin");
        }

        property.setSeller(seller);
        property.setStatus(PropertyStatus.PENDING);

        return propertyRepository.save(property);
    }

    public List<Property> getApprovedProperties(){
        return propertyRepository.findByStatus(PropertyStatus.APPROVED);
    }

    public Property updateProperty(Long propertyId, Property updatedProperty){

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        property.setTitle(updatedProperty.getTitle());
        property.setPrice(updatedProperty.getPrice());
        property.setConfiguration(updatedProperty.getConfiguration());

        return propertyRepository.save(property);
    }

}