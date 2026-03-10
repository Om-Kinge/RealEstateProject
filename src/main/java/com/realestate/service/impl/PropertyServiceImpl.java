package com.realestate.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.realestate.entity.Property;
import com.realestate.entity.PropertyStatus;
import com.realestate.entity.SellerStatus;
import com.realestate.entity.User;
import com.realestate.exception.PropertyNotFoundException;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import com.realestate.service.PropertyService;
import com.realestate.specification.PropertySpecification;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private UserRepository userRepository;

	public Property createProperty(Property property, Long sellerId) {

		User seller = userRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("Seller not found"));

		if (!seller.isPaid()) {
			throw new RuntimeException("Seller must complete payment");
		}

		if (seller.getSellerStatus() != SellerStatus.APPROVED) {
			throw new RuntimeException("Seller not approved by admin");
		}

		property.setSeller(seller);
		property.setStatus(PropertyStatus.PENDING);

		return propertyRepository.save(property);
	}

	public List<Property> getApprovedProperties() {
		return propertyRepository.findByStatus(PropertyStatus.APPROVED);
	}

	public Property updateProperty(Long propertyId, Property updatedProperty) {

		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new PropertyNotFoundException("Property not found"));

		property.setTitle(updatedProperty.getTitle());
		property.setPrice(updatedProperty.getPrice());
		property.setConfiguration(updatedProperty.getConfiguration());

		return propertyRepository.save(property);
	}

	public List<Property> searchProperties(String location, String configuration, Double minPrice, Double maxPrice,
			LocalDate possessionDate) {

		Specification<Property> spec = Specification.where(PropertySpecification.hasLocation(location))
				.and(PropertySpecification.hasConfiguration(configuration))
				.and(PropertySpecification.priceBetween(minPrice, maxPrice))
				.and(PropertySpecification.possessionBefore(possessionDate));

		return propertyRepository.findAll(spec);

	}
}
