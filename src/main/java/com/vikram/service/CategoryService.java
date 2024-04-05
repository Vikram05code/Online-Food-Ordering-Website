package com.vikram.service;

import com.vikram.Exception.RestaurantException;
import com.vikram.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory (String name, Long userId) throws RestaurantException;
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
    public Category findCategoryById(Long id) throws RestaurantException;

}