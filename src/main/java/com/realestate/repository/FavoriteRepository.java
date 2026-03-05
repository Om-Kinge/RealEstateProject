
package com.realestate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
	 List<Favorite> findByBuyerId(Long buyerId);
	 Optional<Favorite> findByBuyerIdAndPropertyId(Long buyerId, Long propertyId);
}
