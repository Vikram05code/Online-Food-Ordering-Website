package com.vikram.service;

import com.vikram.Exception.RestaurantException;
import com.vikram.model.IngredientCategory;
import com.vikram.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws RestaurantException;

    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(
            Long restaurantId);


    public IngredientsItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName,Long ingredientCategoryId) throws Exception;

    public IngredientsItem updateStoke(Long id) throws Exception;

}