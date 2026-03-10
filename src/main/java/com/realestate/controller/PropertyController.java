package com.realestate.controller;

import com.realestate.entity.Property;
import com.realestate.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	// View all approved properties
	@GetMapping
	public List<Property> getAllApprovedProperties() {
		return propertyService.getApprovedProperties();
	}

	// SELLER: Create property
	@PostMapping("/seller/{sellerId}")
	public Property createProperty(@PathVariable Long sellerId, @RequestBody Property property) {

		return propertyService.createProperty(property, sellerId);
	}

	// SELLER: Update property
	@PutMapping("/{propertyId}")
	public Property updateProperty(@PathVariable Long propertyId, @RequestBody Property property) {

		return propertyService.updateProperty(propertyId, property);
	}

	@GetMapping("/search")
	public List<Property> searchProperties(

			@RequestParam(required = false) String location, @RequestParam(required = false) String configuration,
			@RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice,
			@RequestParam(required = false) String possessionDate) {

		LocalDate date = possessionDate != null ? LocalDate.parse(possessionDate) : null;

		return propertyService.searchProperties(location, configuration, minPrice, maxPrice, date);
	}

}