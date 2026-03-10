package com.realestate.service;

import com.realestate.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface PropertyService {

    public Property createProperty(Property property, Long sellerId);

    public List<Property> getApprovedProperties();

    public Property updateProperty(Long propertyId, Property updatedProperty);
    
    public List<Property> searchProperties(String location, String configuration, Double minPrice, Double maxPrice,
			LocalDate possessionDate);
    
}