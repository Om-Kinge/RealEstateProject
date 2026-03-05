package com.realestate.controller;

import com.realestate.entity.Favorite;
import com.realestate.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;


    @PostMapping("/{propertyId}")
    public Favorite addFavorite(
            @RequestParam Long buyerId,
            @PathVariable Long propertyId){

        return favoriteService.addFavorite(buyerId, propertyId);
    }


    @GetMapping
    public List<Favorite> getFavorites(@RequestParam Long buyerId){

        return favoriteService.getFavorites(buyerId);
    }


    @DeleteMapping("/{propertyId}")
    public String removeFavorite(
            @RequestParam Long buyerId,
            @PathVariable Long propertyId){

        favoriteService.removeFavorite(buyerId, propertyId);

        return "Favorite removed";
    }
}