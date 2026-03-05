package com.realestate.service;

import com.realestate.entity.*;
import com.realestate.repository.FavoriteRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Favorite addFavorite(Long buyerId, Long propertyId){

        if(favoriteRepository
                .findByBuyerIdAndPropertyId(buyerId, propertyId)
                .isPresent()){
            throw new RuntimeException("Property already in favorites");
        }

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        Favorite favorite = new Favorite();
        favorite.setBuyer(buyer);
        favorite.setProperty(property);

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavorites(Long buyerId){

        return favoriteRepository.findByBuyerId(buyerId);
    }

    public void removeFavorite(Long buyerId, Long propertyId){

        Favorite favorite = favoriteRepository
                .findByBuyerIdAndPropertyId(buyerId, propertyId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }
}