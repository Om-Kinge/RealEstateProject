
package com.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.entity.Property;
import com.realestate.entity.PropertyStatus;

public interface PropertyRepository extends JpaRepository<Property,Long> {
	List<Property> findByStatus(PropertyStatus status);
}
