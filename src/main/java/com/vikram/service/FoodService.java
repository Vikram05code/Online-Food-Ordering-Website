package com.vikram.service;

import com.vikram.Exception.FoodException;
import com.vikram.Exception.RestaurantException;
import com.vikram.model.Category;
import com.vikram.model.Food;
import com.vikram.model.Restaurant;
import com.vikram.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category,
                           Restaurant restaurant) throws FoodException, RestaurantException;

    void deleteFood(Long foodId) throws FoodException;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException;

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws FoodException;

    public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}