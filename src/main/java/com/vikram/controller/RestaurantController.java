package com.vikram.controller;

import com.vikram.Exception.RestaurantException;
import com.vikram.Exception.UserException;
import com.vikram.dto.RestaurantDto;
import com.vikram.model.Restaurant;
import com.vikram.model.User;
import com.vikram.service.RestaurantService;
import com.vikram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
   private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> findRestaurantByName(@RequestParam String keyword){
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long id) throws RestaurantException {

        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    public ResponseEntity<RestaurantDto> addToFavorite(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws UserException, RestaurantException {

        User user = userService.findUserProfileByJwt(jwt);
        RestaurantDto restaurantDto = restaurantService.addToFavorites(id, user);

        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }


}
