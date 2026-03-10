package com.realestate.specification;

import com.realestate.entity.Property;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PropertySpecification {

    public static Specification<Property> hasLocation(String location) {
        return (root, query, cb) ->
                location == null ? null :
                        cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }

    public static Specification<Property> hasConfiguration(String configuration) {
        return (root, query, cb) ->
                configuration == null ? null :
                        cb.equal(root.get("configuration"), configuration);
    }

    public static Specification<Property> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) ->
                (minPrice == null || maxPrice == null) ? null :
                        cb.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Property> possessionBefore(LocalDate possessionDate) {
        return (root, query, cb) ->
                possessionDate == null ? null :
                        cb.lessThanOrEqualTo(root.get("possessionDate"), possessionDate);
    }
}
